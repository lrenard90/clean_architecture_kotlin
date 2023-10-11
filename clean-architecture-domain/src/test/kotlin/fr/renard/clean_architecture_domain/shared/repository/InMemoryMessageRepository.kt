package fr.renard.clean_architecture_domain.shared.repository

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import java.util.UUID

class InMemoryMessageRepository : MessageRepository {

    private var messagesById: HashMap<UUID, Message> = HashMap()

    override fun save(message: Message): Message {
        messagesById[message.id] = message
        return message
    }

    fun messages(): Collection<Message> {
        return messagesById.values
    }
}