package fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.repository

import fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.entity.MessageJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MessageJpaEntityHibernateRepository: JpaRepository<MessageJpaEntity, UUID> {
    fun findAllByAuthor(author: String): List<MessageJpaEntity>
}