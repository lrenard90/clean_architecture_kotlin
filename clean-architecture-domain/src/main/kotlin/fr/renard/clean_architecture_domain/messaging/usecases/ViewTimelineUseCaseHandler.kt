package fr.renard.clean_architecture_domain.messaging.usecases

import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import fr.renard.clean_architecture_domain.messaging.usecases.dto.GetTimelineRequestDTO
import fr.renard.clean_architecture_domain.messaging.usecases.dto.TimelineMessageDTO
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase
import fr.renard.clean_architecture_domain.socle.time.DateProvider
import java.time.LocalDateTime

@UseCase
class ViewTimelineUseCaseHandler(
    private val messageRepository: MessageRepository,
    private val dateProvider: DateProvider,
) {
    fun handle(getTimelineRequestDTO: GetTimelineRequestDTO): List<TimelineMessageDTO> {
        return messageRepository.findAllByAuthor(getTimelineRequestDTO.author)
            .map { TimelineMessageDTO(it.id, it.author, it.text, computePublishedDateString(it.publishedDate)) }
    }

    private fun computePublishedDateString(publishedDate: LocalDateTime): String {
        val now = dateProvider.now()
        val minutesDifference = publishedDate.until(now, java.time.temporal.ChronoUnit.MINUTES)
        return "$minutesDifference minute${if (minutesDifference > 1) "s" else ""} ago"
    }
}