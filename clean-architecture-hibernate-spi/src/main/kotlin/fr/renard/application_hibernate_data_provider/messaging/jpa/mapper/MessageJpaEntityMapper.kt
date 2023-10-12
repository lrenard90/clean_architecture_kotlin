package fr.renard.application_hibernate_data_provider.messaging.jpa.mapper

import fr.renard.application_hibernate_data_provider.messaging.jpa.entity.MessageJpaEntity
import fr.renard.clean_architecture_domain.messaging.model.entity.MessageState
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface MessageJpaEntityMapper {
    fun toJpaEntity(messageState: MessageState): MessageJpaEntity
    fun toState(messageJpaEntity: MessageJpaEntity): MessageState
}