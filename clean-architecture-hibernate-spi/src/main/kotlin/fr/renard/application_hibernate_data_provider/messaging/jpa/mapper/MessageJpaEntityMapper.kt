package fr.renard.application_hibernate_data_provider.messaging.jpa.mapper

import fr.renard.application_hibernate_data_provider.messaging.jpa.entity.MessageJpaEntity
import fr.renard.clean_architecture_domain.messaging.domain.entity.MessageData
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface MessageJpaEntityMapper {
    fun toJpaEntity(messageData: MessageData): MessageJpaEntity
    fun toData(messageJpaEntity: MessageJpaEntity): MessageData
}