package fr.renard.clean_architecture_application.unit.messaging

import fr.renard.clean_architecture_application.messaging.application.dto.PostMessageRequestDTO
import fr.renard.clean_architecture_application.unit.messaging.fixtures.MessagingFixture
import fr.renard.clean_architecture_application.messaging.builders.MessageBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

@DisplayName("Feature: Post a message")
class PostMessageUseCaseHandlerTest {
    private lateinit var messagingFixture: MessagingFixture

    @BeforeEach
    fun setup() {
        messagingFixture = MessagingFixture()
    }

    @Test
    @DisplayName("Scenario: User can post a valid message")
    fun postValidMessage() {
        messagingFixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

        messagingFixture.whenUserPostsAMessage(
            PostMessageRequestDTO(
                UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33318"),
                "Alice",
                "Hello world!"
            )
        );

        messagingFixture.thenPostedMessageShouldBe(
            MessageBuilder()
                .withId(UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33318"))
                .withAuthor("Alice")
                .withText("Hello world!")
                .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
                .build()
        );
    }

    @Nested
    @DisplayName("Rule: a message size is limited to 280 characters")
    inner class MessageSizeLimitation {
        @Test
        fun `User cannot post a message with more than 280 characters`() {
            messagingFixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            messagingFixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    UUID.randomUUID(),
                    "Alice",
                    "a".repeat(281)
                )
            )

            messagingFixture.thenErrorShouldBe("Message text must be less than 280 characters")
        }
    }

    @Nested
    @DisplayName("Rule: a message cannot be empty")
    inner class MessageNotEmpty {
        @Test
        fun `User cannot post an empty message`() {
            messagingFixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            messagingFixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    UUID.randomUUID(),
                    "Alice",
                    ""
                )
            )

            messagingFixture.thenErrorShouldBe("Message text must not be blank")
        }

        @Test
        fun `User cannot post an blank message`() {
            messagingFixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51));

            messagingFixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    UUID.randomUUID(),
                    "Alice",
                    " "
                )
            )

            messagingFixture.thenErrorShouldBe("Message text must not be blank")
        }
    }

    @Nested
    @DisplayName("Scenario: id already exists")
    inner class IdAlreadyExists {
        @Test
        fun `User cannot post a message with an id that already exists`() {
            val existingMessageId = UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33318")
            messagingFixture.givenTheFollowingMessagesExists(
                listOf(
                    MessageBuilder()
                        .withId(existingMessageId)
                        .build()
                )
            )

            messagingFixture.whenUserPostsAMessage(
                PostMessageRequestDTO(
                    existingMessageId,
                    "Alice",
                    "Hello world!"
                )
            )

            messagingFixture.thenErrorShouldBe("Message already exists")
        }
    }
}