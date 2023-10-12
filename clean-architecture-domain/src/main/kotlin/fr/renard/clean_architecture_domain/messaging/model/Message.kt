package fr.renard.clean_architecture_domain.messaging.model

import fr.renard.clean_architecture_domain.socle.model.Snapshotable
import java.time.LocalDateTime
import java.util.UUID

class Message: Snapshotable<MessageState> {
    val id: UUID
    val author: String
    lateinit var text: String
    val publishedDate: LocalDateTime

    constructor(id: UUID, author: String, text: String, publishedDate: LocalDateTime) {
        this.id = id
        this.author = author
        this.updateText(text)
        this.publishedDate = publishedDate
    }

    constructor(state: MessageState) {
        this.id = state.id
        this.author = state.author
        this.text = state.text
        this.publishedDate = state.publishedDate
    }

    override fun snapshot(): MessageState {
        return MessageState(id, author, text, publishedDate)
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

    fun updateText(text: String) {
        if (text.isBlank()) throw IllegalArgumentException("Message text must not be blank")
        if (text.length > 280) throw IllegalArgumentException("Message text must be less than 280 characters")

        this.text = text
    }
}