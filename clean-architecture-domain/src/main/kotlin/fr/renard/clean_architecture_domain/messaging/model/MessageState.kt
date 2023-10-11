package fr.renard.clean_architecture_domain.messaging.model

import java.time.LocalDateTime
import java.util.*

data class MessageState(val id: UUID, val author: String, val text: String, val publishedDate: LocalDateTime) {
}