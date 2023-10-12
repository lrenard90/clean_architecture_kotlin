package fr.renard.clean_architecture_domain.unit.messaging.shared.builders

import fr.renard.clean_architecture_domain.messaging.model.entity.Message
import java.time.LocalDateTime
import java.util.*

class MessageBuilder {
    private var id: UUID = UUID.fromString("de048ea1-0149-4a9f-bcdd-af8d402d33c7")
    private var author: String = "Alice"
    private var text: String = "Hello world!"
    private var publishedDate: LocalDateTime = LocalDateTime.of(2020, 2, 14, 17, 46, 51)

    fun withId(id: UUID): MessageBuilder {
        this.id = id
        return this
    }

    fun withAuthor(author: String): MessageBuilder {
        this.author = author
        return this
    }

    fun withText(text: String): MessageBuilder {
        this.text = text
        return this
    }

    fun withPublishedDate(publishedDate: LocalDateTime): MessageBuilder {
        this.publishedDate = publishedDate
        return this
    }

    fun build(): Message {
        return Message(
            id,
            author,
            text,
            publishedDate
        )
    }
}