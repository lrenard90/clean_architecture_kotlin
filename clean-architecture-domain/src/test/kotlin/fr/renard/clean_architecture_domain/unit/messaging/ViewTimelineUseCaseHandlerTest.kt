package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.messaging.usecases.dto.TimelineMessageDTO
import fr.renard.clean_architecture_domain.unit.messaging.shared.MessagingFixture
import fr.renard.clean_architecture_domain.unit.messaging.shared.builders.MessageBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test

@DisplayName("Feature: View messages timeline")
class ViewTimelineUseCaseHandlerTest {
    private lateinit var messagingFixture: MessagingFixture

    @BeforeEach
    fun setUp() {
        messagingFixture = MessagingFixture()
    }

    @Nested
    @DisplayName("Scenario: User view his empty timeline")
    inner class NoMessage {
        @Test
        fun `User should get empty timeline`() {
            messagingFixture.givenTheFollowingMessagesExists(emptyList())
            messagingFixture.whenUserSeesTimelineOf("Alice")
            messagingFixture.thenUserGetsAnEmptyTimeline()
        }
    }

    @Nested
    @DisplayName("Rule: Messages in the timeline are displayed in reverse chronological order")
    inner class MessagesTimelineReversed {
        @Test
        fun `User should get his timeline`() {
            messagingFixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
            messagingFixture.givenTheFollowingMessagesExists(
                listOf(
                    MessageBuilder()
                        .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                        .withAuthor("Alice")
                        .withText("Hello world!")
                        .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 45, 51))
                        .build(),
                    MessageBuilder()
                        .withId(UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33319"))
                        .withAuthor("Alice")
                        .withText("Hello world!")
                        .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 44, 51))
                        .build()
                )
            )

            messagingFixture.whenUserSeesTimelineOf("Alice")

            messagingFixture.thenUserShouldSee(
                listOf(
                    TimelineMessageDTO(
                        UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                        "Alice",
                        "Hello world!",
                        "1 minute ago"
                    ),
                    TimelineMessageDTO(
                        UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33319"),
                        "Alice",
                        "Hello world!",
                        "2 minutes ago"
                    )
                )
            )
        }
    }

    @Nested
    @DisplayName("Rule: Timeline displays only messages of specified user")
    inner class FilterAuthorTimeline {
        @Test
        fun `User should get user only timeline`() {
            messagingFixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
            messagingFixture.givenTheFollowingMessagesExists(
                listOf(
                    MessageBuilder()
                        .withId(UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"))
                        .withAuthor("Alice")
                        .withText("Hello world!")
                        .withPublishedDate(LocalDateTime.of(2020, 2, 14, 17, 45, 51))
                        .build(),
                    MessageBuilder()
                        .withAuthor("Bob")
                        .withText("Hello world!")
                        .build()
                )
            )

            messagingFixture.whenUserSeesTimelineOf("Alice")

            messagingFixture.thenUserShouldSee(
                listOf(
                    TimelineMessageDTO(
                        UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                        "Alice",
                        "Hello world!",
                        "1 minute ago"
                    )
                )
            )
        }
    }
}