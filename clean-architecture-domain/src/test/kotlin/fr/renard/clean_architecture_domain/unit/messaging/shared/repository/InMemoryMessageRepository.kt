package fr.renard.clean_architecture_domain.unit.messaging.shared.repository

import fr.renard.clean_architecture_domain.messaging.model.entity.Message
import fr.renard.clean_architecture_domain.messaging.model.entity.MessageData
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import java.util.*
import kotlin.collections.HashMap

class InMemoryMessageRepository : MessageRepository {
    private var messagesById: HashMap<UUID, Message> = HashMap()

    override fun save(message: Message): Message {
        messagesById[message.id] = copy(message)
        return message
    }

    override fun findAllByAuthor(author: String): List<Message> {
        return messagesById.values.filter { it.author == author }
    }

    override fun findById(messageId: UUID): Optional<Message> {
        return Optional.ofNullable(messagesById[messageId])
    }

    fun messages(): Collection<Message> {
        return messagesById.values
    }

    fun setMessages(messages: List<Message>) {
        messagesById = HashMap(messages.associateBy { it.id })
    }

    private fun copy(message: Message): Message {
        // We use data structure instance creation to avoid validation errors and be sure the validation logic is in the hexagon and not in this in memory test double
        return Message.fromData(MessageData(message.id, message.author, message.text.value, message.publishedDate))
    }

    fun get(id: UUID): Message {
        return messagesById.values.first()
    }

    override fun existsById(id: UUID): Boolean {
        return messagesById.containsKey(id)
    }
}