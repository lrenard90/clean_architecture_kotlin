package fr.renard.clean_architecture_application.unit.messaging.fixtures

import fr.renard.clean_architecture_application.messaging.domain.entity.Message
import fr.renard.clean_architecture_application.messaging.application.usecases.EditMessageUseCaseHandler
import fr.renard.clean_architecture_application.messaging.application.usecases.PostMessageUseCaseHandler
import fr.renard.clean_architecture_application.messaging.application.usecases.ViewTimelineUseCaseHandler
import fr.renard.clean_architecture_application.messaging.application.dto.EditMessageRequestDTO
import fr.renard.clean_architecture_application.messaging.application.dto.GetTimelineRequestDTO
import fr.renard.clean_architecture_application.messaging.application.dto.PostMessageRequestDTO
import fr.renard.clean_architecture_application.messaging.application.dto.TimelineMessageDTO
import fr.renard.clean_architecture_application.unit.messaging.shared.repository.InMemoryMessageRepository
import fr.renard.clean_architecture_application.shared.time.FakeDateProvider
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDateTime
import java.util.*

class MessagingFixture {
    private val messageRepository = InMemoryMessageRepository()
    private val dateProvider = FakeDateProvider()
    private val postMessageUseCaseHandler = PostMessageUseCaseHandler(messageRepository, dateProvider)
    private val viewTimelineUseCaseHandler = ViewTimelineUseCaseHandler(messageRepository, dateProvider)
    private val editMessageUserCaseHandler: EditMessageUseCaseHandler = EditMessageUseCaseHandler(messageRepository)
    private var errorMessage: String? = null
    lateinit var timelineMessages: List<TimelineMessageDTO>

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
                .map { message: Message -> message.data() }).contains(expectedMessage.data())
    }

    fun thenErrorShouldBe(error: String) {
        assertThat(errorMessage).isEqualTo(error)
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

    fun whenUserEditHisMessage(messageId: UUID, text: String) {
        try {
            editMessageUserCaseHandler.handle(EditMessageRequestDTO(messageId, text))
        } catch (exception: Exception) {
            errorMessage = exception.message
        }
    }

    fun thenMessageShouldBe(message: Message) {
        assertThat(messageRepository.get(message.id).data()).isEqualTo(message.data())
    }
}