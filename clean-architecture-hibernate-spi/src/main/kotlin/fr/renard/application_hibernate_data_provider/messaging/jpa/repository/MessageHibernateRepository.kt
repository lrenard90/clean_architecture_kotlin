package fr.renard.application_hibernate_data_provider.messaging.jpa.repository

import fr.renard.application_hibernate_data_provider.messaging.jpa.entity.MessageJpaEntity
import fr.renard.application_hibernate_data_provider.messaging.jpa.mapper.MessageJpaEntityMapper
import fr.renard.clean_architecture_domain.messaging.model.entity.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MessageHibernateRepository(val messageJpaEntityHibernateRepository: MessageJpaEntityHibernateRepository, val messageJpaEntityMapper: MessageJpaEntityMapper) :
    MessageRepository {

    override fun save(message: Message): Message {
        val messageJpaEntity: MessageJpaEntity = messageJpaEntityMapper.toJpaEntity(message.snapshot())
        val savedJpaEntity: MessageJpaEntity = messageJpaEntityHibernateRepository.save(messageJpaEntity)
        return Message(messageJpaEntityMapper.toState(savedJpaEntity))
    }

    override fun findAllByAuthor(author: String): List<Message> {
        val messageJpaEntities: List<MessageJpaEntity> = messageJpaEntityHibernateRepository.findAllByAuthor(author)
        return messageJpaEntities.map { messageJpaEntity: MessageJpaEntity -> Message(messageJpaEntityMapper.toState(messageJpaEntity)) }
    }

    override fun findById(messageId: UUID): Optional<Message> {
        return messageJpaEntityHibernateRepository.findById(messageId)
            .map { messageJpaEntity: MessageJpaEntity -> Message(messageJpaEntityMapper.toState(messageJpaEntity)) }
    }

    override fun existsById(id: UUID): Boolean {
        return messageJpaEntityHibernateRepository.existsById(id)
    }

}