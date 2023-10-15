package fr.renard.clean_architecture_application.unit.messaging

import fr.renard.clean_architecture_application.messaging.application.dto.FollowUserRequestDTO
import fr.renard.clean_architecture_application.messaging.application.usecases.FollowUserUseCaseHandler
import fr.renard.clean_architecture_application.messaging.domain.entity.UserFollowSettings
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FollowUserUseCaseHandlerTest {

    private lateinit var fixture: FollowUserFixture

    @BeforeEach
    fun setUp() {
        fixture = FollowUserFixture()
    }

    @Test
    fun `user can follow another one`() {
        fixture.givenCurrentUserIs("Alice")
        fixture.whenUserFollows("Bob")
        fixture.thenUserFollowSettingsAre(
            UserFollowSettings("Alice", listOf("Bob"))
        )
    }

    inner class FollowUserFixture {
        private val authenticationGateway = AuthenticationGateway()
        private val followUserUseCaseHandler = FollowUserUseCaseHandler()

        fun givenCurrentUserIs(currentUser: String) {

        }

        fun whenUserFollows(userToFollow: String) {
            followUserUseCaseHandler.handle(FollowUserRequestDTO(userToFollow))
        }

        fun thenUserFollowSettingsAre(userFollowSettings: UserFollowSettings) {
            TODO("Not yet implemented")
        }
    }
}