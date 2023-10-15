package fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

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