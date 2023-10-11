package fr.renard.clean_architecture_domain.unit.messaging

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.usecases.ViewTimelineUseCaseHandler
import fr.renard.clean_architecture_domain.shared.repository.InMemoryMessageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Feature: View messages timeline")
class ViewTimelineUseCaseHandlerTest {

    private lateinit var fixture: TestFixture

    @BeforeEach
    fun setUp() {
        fixture = TestFixture(InMemoryMessageRepository())
    }

    @Nested
    @DisplayName("When no message")
    inner class NoMessage {
        @Test
        fun `User should get empty timeline`() {
            fixture.givenTheFollowingMessagesExists(emptyList())

            fixture.whenIViewMyTimeline()

            fixture.thenIGetAnEmptyTimeline()
        }
    }

    inner class TestFixture(val messageRepository: InMemoryMessageRepository) {
        private val viewTimelineUseCaseHandler = ViewTimelineUseCaseHandler(messageRepository)

        fun givenTheFollowingMessagesExists(messages: List<Message>) {
            messageRepository.setMessages(messages)
        }

        fun whenIViewMyTimeline() {
            viewTimelineUseCaseHandler.handle()
        }

        fun thenIGetAnEmptyTimeline() {
            assertThat(messageRepository.messages()).isEmpty();
        }
    }
}