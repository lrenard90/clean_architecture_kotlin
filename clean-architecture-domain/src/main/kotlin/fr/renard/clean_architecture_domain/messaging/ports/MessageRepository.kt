package fr.renard.clean_architecture_domain.messaging.ports

import fr.renard.clean_architecture_domain.messaging.model.Message

interface MessageRepository {

    fun save(message: Message): Message
    fun findAllByAuthor(author: String): List<Message>

}