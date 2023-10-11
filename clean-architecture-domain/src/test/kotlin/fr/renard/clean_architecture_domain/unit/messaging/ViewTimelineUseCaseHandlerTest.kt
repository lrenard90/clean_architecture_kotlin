package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.usecases.ViewTimelineUseCaseHandler
import fr.renard.clean_architecture_domain.messaging.usecases.dto.GetTimelineRequestDTO
import fr.renard.clean_architecture_domain.messaging.usecases.dto.TimelineMessageDTO
import fr.renard.clean_architecture_domain.shared.repository.InMemoryMessageRepository
import fr.renard.clean_architecture_domain.shared.time.FakeDateProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test

@DisplayName("Feature: View messages timeline")
class ViewTimelineUseCaseHandlerTest {

    private lateinit var fixture: TestFixture

    @BeforeEach
    fun setUp() {
        fixture = TestFixture()
    }

    @Nested
    @DisplayName("Scenario: User view his empty timeline")
    inner class NoMessage {
        @Test
        fun `User should get empty timeline`() {
            fixture.givenTheFollowingMessagesExists(emptyList())

            fixture.whenUserSeesTimelineOf("Alice")

            fixture.thenUserGetsAnEmptyTimeline()
        }
    }

    @Nested
    @DisplayName("Scenario: User can view his messages in the timeline")
    inner class MessagesTimeline {
        @Test
        fun `User should get his timeline`() {
            fixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
            fixture.givenTheFollowingMessagesExists(
                listOf(
                    Message(
                        UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                        "Alice",
                        "Hello world!",
                        LocalDateTime.of(2020, 2, 14, 17, 45, 51)
                    ),
                    Message(
                        UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33319"),
                        "Alice",
                        "Hello world!",
                        LocalDateTime.of(2020, 2, 14, 17, 44, 51)
                    )
                )
            )

            fixture.whenUserSeesTimelineOf("Alice")

            fixture.thenUserShouldSee(listOf(
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
            ))
        }
    }

    @Nested
    @DisplayName("Scenario: Timeline displays only messages of specified user")
    inner class FilterAuthorTimeline {
        @Test
        fun `User should get user only timeline`() {
            fixture.givenNowIs(LocalDateTime.of(2020, 2, 14, 17, 46, 51))
            fixture.givenTheFollowingMessagesExists(
                listOf(
                    Message(
                        UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                        "Alice",
                        "Hello world!",
                        LocalDateTime.of(2020, 2, 14, 17, 45, 51)
                    ),
                    Message(
                        UUID.fromString("e1fd6ad4-83d5-4f8d-a788-0132c9b33319"),
                        "Bob",
                        "Hello world!",
                        LocalDateTime.of(2020, 2, 14, 17, 44, 51)
                    )
                )
            )

            fixture.whenUserSeesTimelineOf("Alice")

            fixture.thenUserShouldSee(listOf(
                TimelineMessageDTO(
                    UUID.fromString("cc865b1a-529a-4973-9d0b-58ca894f98a2"),
                    "Alice",
                    "Hello world!",
                    "1 minute ago"
                )
            ))
        }
    }

    inner class TestFixture {
        private val messageRepository = InMemoryMessageRepository()
        private val dateProvider = FakeDateProvider()
        private val viewTimelineUseCaseHandler = ViewTimelineUseCaseHandler(messageRepository, dateProvider)

        private lateinit var timelineMessages: List<TimelineMessageDTO>

        fun givenNowIs(now: LocalDateTime) {
            dateProvider.now = now
        }

        fun givenTheFollowingMessagesExists(messages: List<Message>) {
            messageRepository.setMessages(messages)
        }

        fun whenUserSeesTimelineOf(author: String) {
            timelineMessages = viewTimelineUseCaseHandler.handle(GetTimelineRequestDTO(author))
        }

        fun thenUserGetsAnEmptyTimeline() {
            assertThat(timelineMessages).isEmpty();
        }

        fun thenUserShouldSee(timelineMessages: List<TimelineMessageDTO>) {
            assertThat(this.timelineMessages).containsExactlyElementsOf(timelineMessages)
        }
    }
}