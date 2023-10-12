package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.unit.messaging.shared.MessagingFixture
import fr.renard.clean_architecture_domain.unit.messaging.shared.builders.MessageBuilder
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
            .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
            .withAuthor("Alice")
            .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
        messagingFixture.givenTheFollowingMessagesExists(
            listOf(
                aliceMessageBuilder
                    .withText("Hello world!")
                    .build()
            )
        )

        messagingFixture.whenUserEditHisMessage(
            UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
            "Hello world! I'm Alice"
        )

        messagingFixture.thenMessageShouldBe(
            aliceMessageBuilder
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
}