package fr.renard.clean_architecture_domain.messaging.ports

import fr.renard.clean_architecture_domain.messaging.model.Message
import java.util.*

interface MessageRepository {
    fun save(message: Message): Message
    fun findAllByAuthor(author: String): List<Message>
    fun findById(messageId: UUID): Optional<Message>
}