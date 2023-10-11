package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.usecases.PostMessageUseCaseHandler
import fr.renard.clean_architecture_domain.messaging.usecases.dto.PostMessageRequest
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

    private lateinit var messageRepository: InMemoryMessageRepository
    private lateinit var dateProvider: FakeDateProvider
    private lateinit var postMessageUseCaseHandler: PostMessageUseCaseHandler
    private lateinit var testContext: TestContext

    @BeforeEach
    fun setup() {
        messageRepository = InMemoryMessageRepository()
        dateProvider = FakeDateProvider()
        postMessageUseCaseHandler = PostMessageUseCaseHandler(messageRepository, dateProvider);
        testContext = TestContext()
    }

    @Test
    fun `User can post a valid message on his timeline`() {
        givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

        whenUserPostsAMessage(
            PostMessageRequest(
                UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33318"),
                "Alice",
                "Hello world!"
            )
        );

        thenPostedMessageShouldBe(
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
            givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            whenUserPostsAMessage(
                PostMessageRequest(
                    UUID.randomUUID(),
                    "Alice",
                    "a".repeat(281)
                )
            )

            thenErrorShouldBe("Message text must be less than 280 characters")
        }
    }

    @Nested
    @DisplayName("Rule: a message cannot be empty")
    inner class MessageNotEmpty {
        @Test
        fun `User cannot post an empty message`() {
            givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            whenUserPostsAMessage(
                PostMessageRequest(
                    UUID.randomUUID(),
                    "Alice",
                    ""
                )
            )

            thenErrorShouldBe("Message text must not be blank")
        }

        @Test
        fun `User cannot post an blank message`() {
            givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            whenUserPostsAMessage(
                PostMessageRequest(
                    UUID.randomUUID(),
                    "Alice",
                    " "
                )
            )

            thenErrorShouldBe("Message text must not be blank")
        }
    }

    private fun givenNowIs(now: LocalDateTime) {
        dateProvider.now = now;
    }

    private fun whenUserPostsAMessage(postMessageRequest: PostMessageRequest) {
        try {
            postMessageUseCaseHandler.handle(postMessageRequest)
        } catch (exception: Exception) {
            testContext.errorMessage = exception.message
        }
    }

    private fun thenPostedMessageShouldBe(expectedMessage: Message) {
        assertThat(
            messageRepository.messages()
                .map { message: Message -> message.snapshot() }).contains(expectedMessage.snapshot())
    }

    private fun thenErrorShouldBe(error: String) {
        assertThat(testContext.errorMessage).isEqualTo(error)
    }

    inner class TestContext(var errorMessage: String? = null) {}
}