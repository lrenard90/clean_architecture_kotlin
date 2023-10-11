package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.usecases.PostMessageUseCaseHandler
import fr.renard.clean_architecture_domain.messaging.usecases.dto.PostMessageRequestDTO
import fr.renard.clean_architecture_domain.shared.time.FakeDateProvider
import fr.renard.clean_architecture_domain.shared.repository.InMemoryMessageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

@DisplayName("Feature: User can post a message")
class PostMessageUseCaseHandlerTest {

    private lateinit var fixture: TestFixture

    @BeforeEach
    fun setup() {
        fixture = TestFixture()
    }

    @Test
    fun `User can post a valid message on his timeline`() {
        fixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

        fixture.whenUserPostsAMessage(
            PostMessageRequestDTO(
                UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33318"),
                "Alice",
                "Hello world!"
            )
        );

        fixture.thenPostedMessageShouldBe(
            Message(
                UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33318"),
                "Alice",
                "Hello world!",
                LocalDateTime.of(2020, 2, 14, 17, 46, 51)
            )
        );
    }

    @Nested
    @DisplayName("Rule: a message size is limited to 280 characters")
    inner class MessageSizeLimitation {
        @Test
        fun `User cannot post a message with more than 280 characters`() {
            fixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            fixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    UUID.randomUUID(),
                    "Alice",
                    "a".repeat(281)
                )
            )

            fixture.thenErrorShouldBe("Message text must be less than 280 characters")
        }
    }

    @Nested
    @DisplayName("Rule: a message cannot be empty")
    inner class MessageNotEmpty {
        @Test
        fun `User cannot post an empty message`() {
            fixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            fixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    UUID.randomUUID(),
                    "Alice",
                    ""
                )
            )

            fixture.thenErrorShouldBe("Message text must not be blank")
        }

        @Test
        fun `User cannot post an blank message`() {
            fixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            fixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    UUID.randomUUID(),
                    "Alice",
                    " "
                )
            )

            fixture.thenErrorShouldBe("Message text must not be blank")
        }
    }

    inner class TestFixture {
        private val messageRepository = InMemoryMessageRepository()
        private val dateProvider = FakeDateProvider()
        private val postMessageUseCaseHandler: PostMessageUseCaseHandler = PostMessageUseCaseHandler(
            messageRepository,
            dateProvider
        )
        private var errorMessage: String? = null

        fun givenNowIs(now: LocalDateTime) {
            dateProvider.now = now;
        }

        fun whenUserPostsAMessage(postMessageRequestDTO: PostMessageRequestDTO) {
            try {
                postMessageUseCaseHandler.handle(postMessageRequestDTO)
            } catch (exception: Exception) {
                errorMessage = exception.message
            }
        }

        fun thenPostedMessageShouldBe(expectedMessage: Message) {
            assertThat(
                messageRepository.messages()
                    .map { message: Message -> message.snapshot() }).contains(expectedMessage.snapshot())
        }

        fun thenErrorShouldBe(error: String) {
            assertThat(errorMessage).isEqualTo(error)
        }
    }
}