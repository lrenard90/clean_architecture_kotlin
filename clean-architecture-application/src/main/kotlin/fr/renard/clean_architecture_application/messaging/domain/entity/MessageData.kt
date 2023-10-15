package fr.renard.clean_architecture_application.messaging.domain.entity

import java.time.LocalDateTime
import java.util.*

data class MessageData(val id: UUID, val author: String, val text: String, val publishedDate: LocalDateTime) {
}