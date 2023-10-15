package fr.renard.clean_architecture_application.messaging.application.usecases

import fr.renard.clean_architecture_application.messaging.application.ports.MessageRepository
import fr.renard.clean_architecture_application.messaging.application.dto.GetTimelineRequestDTO
import fr.renard.clean_architecture_application.messaging.application.dto.TimelineMessageDTO
import fr.renard.clean_architecture_application.socle.dependency_injection.annotation.UseCase
import fr.renard.clean_architecture_application.socle.time.DateProvider
import java.time.LocalDateTime

@UseCase
class ViewTimelineUseCaseHandler(
    private val messageRepository: MessageRepository,
    private val dateProvider: DateProvider,
) {
    fun handle(getTimelineRequestDTO: GetTimelineRequestDTO): List<TimelineMessageDTO> {
        return messageRepository.findAllByAuthor(getTimelineRequestDTO.author)
            .sortedByDescending { it.publishedDate }
            .map { TimelineMessageDTO(it.id, it.author, it.text.value, computePublishedDateString(it.publishedDate)) }
    }

    private fun computePublishedDateString(publishedDate: LocalDateTime): String {
        val now = dateProvider.now()
        val minutesDifference = publishedDate.until(now, java.time.temporal.ChronoUnit.MINUTES)
        return "$minutesDifference minute${if (minutesDifference > 1) "s" else ""} ago"
    }
}