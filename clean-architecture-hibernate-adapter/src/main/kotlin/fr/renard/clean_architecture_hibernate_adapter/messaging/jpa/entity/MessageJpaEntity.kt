package fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "message")
class MessageJpaEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID

    @Column(name = "author")
    var author: String

    @Column(name = "text")
    var text: String

    @Column(name = "published_date")
    var publishedDate: LocalDateTime

    constructor(id: UUID, author: String, text: String, publishedDate: LocalDateTime) {
        this.id = id
        this.author = author
        this.text = text
        this.publishedDate = publishedDate
    }
}