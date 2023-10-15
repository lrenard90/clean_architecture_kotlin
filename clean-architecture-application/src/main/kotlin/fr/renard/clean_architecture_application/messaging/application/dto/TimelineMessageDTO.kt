package fr.renard.clean_architecture_application.messaging.application.dto

import java.util.*

data class TimelineMessageDTO(val id: UUID, val author: String, val text: String, val publicationTime: String) {
}