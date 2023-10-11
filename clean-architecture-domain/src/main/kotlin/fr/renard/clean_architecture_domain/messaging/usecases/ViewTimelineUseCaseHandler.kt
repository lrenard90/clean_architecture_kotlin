package fr.renard.clean_architecture_domain.messaging.usecases

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase

@UseCase
class ViewTimelineUseCaseHandler(
    private val messageRepository: MessageRepository,
) {
    fun handle(): List<Message> {
        return ArrayList();
    }
}