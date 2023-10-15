package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.unit.messaging.shared.MessagingFixture
import fr.renard.clean_architecture_domain.messaging.builders.MessageBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test

@DisplayName("Feature: Edit a message")
class EditMessageUseCaseHandlerTest {

    private lateinit var messagingFixture: MessagingFixture

    @BeforeEach
    fun setUp() {
        messagingFixture = MessagingFixture()
    }

    @Test
    fun `User can edit his message`() {
        val aliceMessageBuilder = MessageBuilder()
            .withAuthor("Alice")
            .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
        messagingFixture.givenTheFollowingMessagesExists(
            listOf(
                aliceMessageBuilder
                    .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                    .withText("Hello world!")
                    .build(),
                aliceMessageBuilder
                    .withId(UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33319"))
                    .withText("Hello!")
                    .build(),
            )
        )

        messagingFixture.whenUserEditHisMessage(
            UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
            "Hello world! I'm Alice"
        )

        messagingFixture.thenMessageShouldBe(
            aliceMessageBuilder
                .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                .withText("Hello world! I'm Alice")
                .build()
        )
    }

    @Nested
    inner class MessageNotFound {
        @Test
        fun `User cannot edit a message that does not exist`() {
            messagingFixture.givenTheFollowingMessagesExists(emptyList())

            messagingFixture.whenUserEditHisMessage(
                UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                "Hello world! I'm Alice"
            )

            messagingFixture.thenErrorShouldBe("Message not found")
        }
    }

    @Nested
    @DisplayName("Rule: a message size is limited to 280 characters")
    inner class MessageSizeLimitation {
        @Test
        fun `User cannot edit a message with more than 280 characters`() {
            messagingFixture.givenTheFollowingMessagesExists(
                listOf(
                    MessageBuilder()
                        .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                        .withAuthor("Alice")
                        .withText("Hello world!")
                        .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
                        .build()
                )
            )

            messagingFixture.whenUserEditHisMessage(
                UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                "a".repeat(281)
            )

            messagingFixture.thenErrorShouldBe("Message text must be less than 280 characters")
        }
    }

    @Nested
    @DisplayName("Rule: a message cannot be empty")
    inner class MessageNotEmpty {
        @Test
        fun `User cannot edit a message with an empty text`() {
            messagingFixture.givenTheFollowingMessagesExists(
                listOf(
                    MessageBuilder()
                        .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                        .withAuthor("Alice")
                        .withText("Hello world!")
                        .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
                        .build()
                )
            )

            messagingFixture.whenUserEditHisMessage(
                UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                ""
            )

            messagingFixture.thenErrorShouldBe("Message text must not be blank")
        }

        @Test
        fun `User cannot edit a message with a blank text`() {
            messagingFixture.givenTheFollowingMessagesExists(
                listOf(
                    MessageBuilder()
                        .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                        .withAuthor("Alice")
                        .withText("Hello world!")
                        .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
                        .build()
                )
            )

            messagingFixture.whenUserEditHisMessage(
                UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                " "
            )

            messagingFixture.thenErrorShouldBe("Message text must not be blank")
        }
    }
}