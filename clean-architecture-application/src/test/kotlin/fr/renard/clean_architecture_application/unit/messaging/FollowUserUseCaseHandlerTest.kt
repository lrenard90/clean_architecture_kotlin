package fr.renard.clean_architecture_application.unit.messaging

import fr.renard.clean_architecture_application.messaging.application.dto.FollowUserRequestDTO
import fr.renard.clean_architecture_application.messaging.application.usecases.FollowUserUseCaseHandler
import fr.renard.clean_architecture_application.messaging.domain.entity.User
import fr.renard.clean_architecture_application.unit.authentication.test_doubles.InMemoryAuthenticationGateway
import fr.renard.clean_architecture_application.unit.messaging.test_doubles.repository.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FollowUserUseCaseHandlerTest {

    private lateinit var fixture: FollowUserFixture

    @BeforeEach
    fun setUp() {
        fixture = FollowUserFixture()
    }

    @Test
    fun `user can follow another one`() {
        fixture.givenUsersExist("Alice", "Bob")
        fixture.givenCurrentUserIs("Alice")

        fixture.whenUserFollows("Bob")

        fixture.thenUserSubscriptionsAre(
            "Alice", listOf("Bob")
        )
    }

    @Test
    fun `user can follow multiple users`() {
        fixture.givenUsersExist("Alice", "Bob", "Jean")
        fixture.givenCurrentUserIs("Alice")

        fixture.whenUserFollows("Bob")
        fixture.whenUserFollows("Jean")

        fixture.thenUserSubscriptionsAre(
            "Alice", listOf("Bob", "Jean")
        )
    }

    @Nested
    inner class `When current user is not found` {
        @BeforeEach
        fun setup() {
            fixture.givenUsersExist("Bob")
            fixture.givenCurrentUserIs("Alice")
        }

        @Test
        fun `it should throw an error`() {
            fixture.whenUserFollows("Bob")
            fixture.thenErrorShouldBe("User Alice not found")
        }
    }

    inner class FollowUserFixture {
        private val authenticationGateway = InMemoryAuthenticationGateway()
        private val userRepository = InMemoryUserRepository()
        private val followUserUseCaseHandler =
            FollowUserUseCaseHandler(authenticationGateway, userRepository)

        private var error: Exception? = null

        fun givenCurrentUserIs(currentUser: String) {
            authenticationGateway.authenticate(currentUser)
        }

        fun whenUserFollows(userToFollow: String) {
            try {
                followUserUseCaseHandler.handle(FollowUserRequestDTO(userToFollow))
            } catch (exception: Exception) {
                error = exception
            }
        }

        fun thenUserSubscriptionsAre(userName: String, subscriptions: List<String>) {
            assertThat(userRepository.get(userName).subscriptions).isEqualTo(subscriptions)
        }

        fun givenUsersExist(vararg users: String) {
            users.forEach { userRepository.feedWith(User(it)) }
        }

        fun thenErrorShouldBe(error: String) {
            assertThat(this.error).hasMessage(error)
        }
    }
}