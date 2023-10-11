package fr.renard.clean_architecture_domain.messaging.usecases.dto

import java.util.*

data class TimelineMessageDTO(val id: UUID, val author: String, val text: String, val publicationTime: String) {
}