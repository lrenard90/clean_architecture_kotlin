package fr.renard.clean_architecture_domain.shared.repository

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import java.util.UUID

class InMemoryMessageRepository : MessageRepository {
    private var messagesById: HashMap<UUID, Message> = HashMap()

    override fun save(message: Message): Message {
        messagesById[message.id] = copy(message)
        return message
    }

    override fun findAllByAuthor(author: String): List<Message> {
        return messagesById.values.filter { it.author == author }
    }

    fun messages(): Collection<Message> {
        return messagesById.values
    }

    fun setMessages(messages: List<Message>) {
        messagesById = HashMap(messages.associateBy { it.id })
    }

    private fun copy(message: Message): Message {
        return Message(message.id, message.author, message.text, message.publishedDate)
    }
}