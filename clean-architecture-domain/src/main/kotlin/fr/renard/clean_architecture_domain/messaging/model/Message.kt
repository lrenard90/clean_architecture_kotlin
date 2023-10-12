package fr.renard.clean_architecture_domain.messaging.model

import fr.renard.clean_architecture_domain.socle.model.Snapshotable
import java.time.LocalDateTime
import java.util.UUID

class Message(val id: UUID, val author: String, var text: String, val publishedDate: LocalDateTime): Snapshotable<MessageState> {

    init {
        if (text.isBlank()) throw IllegalArgumentException("Message text must not be blank")
        if (text.length > 280) throw IllegalArgumentException("Message text must be less than 280 characters")
    }

    constructor(state: MessageState): this(state.id, state.author, state.text, state.publishedDate) {
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
        this.text = text
    }
}