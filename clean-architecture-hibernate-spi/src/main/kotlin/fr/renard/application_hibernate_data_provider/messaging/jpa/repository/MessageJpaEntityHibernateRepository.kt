package fr.renard.application_hibernate_data_provider.messaging.jpa.repository

import fr.renard.application_hibernate_data_provider.messaging.jpa.entity.MessageJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MessageJpaEntityHibernateRepository: JpaRepository<MessageJpaEntity, UUID> {
}