package fr.renard.clean_architecture_application.messaging.application.ports

import fr.renard.clean_architecture_application.messaging.domain.entity.Message
import java.util.*

interface MessageRepository {
    fun save(message: Message): Message
    fun findAllByAuthor(author: String): List<Message>
    fun findById(messageId: UUID): Optional<Message>
    fun existsById(id: UUID): Boolean
}