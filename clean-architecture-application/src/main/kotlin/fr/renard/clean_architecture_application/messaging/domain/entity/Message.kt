package fr.renard.clean_architecture_application.messaging.domain.entity

import fr.renard.clean_architecture_application.messaging.domain.value_object.MessageText
import fr.renard.clean_architecture_application.socle.data.Snapshotable
import java.time.LocalDateTime
import java.util.UUID

class Message: Snapshotable<MessageData> {
    val id: UUID
    val author: String
    lateinit var text: MessageText
    val publishedDate: LocalDateTime

    constructor(id: UUID, author: String, text: String, publishedDate: LocalDateTime) {
        this.id = id
        this.author = author
        this.editText(text)
        this.publishedDate = publishedDate
    }

    override fun equals(other: Any?): Boolean {
        return other is Message && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Message(id=$id, author='$author', text='$text', publishedDate=$publishedDate)"
    }

    fun editText(text: String) {
        this.text = MessageText(text)
    }

    override fun data(): MessageData {
        return MessageData(id, author, text.value, publishedDate)
    }

    companion object {
        fun fromData(state: MessageData): Message {
            return Message(state.id, state.author, state.text, state.publishedDate)
        }
    }
}