package fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.mapper

import fr.renard.clean_architecture_hibernate_adapter.messaging.jpa.entity.MessageJpaEntity
import fr.renard.clean_architecture_application.messaging.domain.entity.MessageData
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface MessageJpaEntityMapper {
    fun toJpaEntity(messageData: MessageData): MessageJpaEntity
    fun toData(messageJpaEntity: MessageJpaEntity): MessageData
}