package fr.renard.clean_architecture_domain.messaging.usecases

import fr.renard.clean_architecture_domain.socle.time.DateProvider
import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import fr.renard.clean_architecture_domain.messaging.usecases.dto.PostMessageRequest
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase

@UseCase
class PostMessageUseCaseHandler(
    private val messageRepository: MessageRepository,
    private val dateProvider: DateProvider,
) {
    fun handle(postMessageRequest: PostMessageRequest) {
        val messageToCreate = Message(
            postMessageRequest.id,
            postMessageRequest.author,
            postMessageRequest.text,
            dateProvider.now()
        )

        messageRepository.save(messageToCreate)
    }
}