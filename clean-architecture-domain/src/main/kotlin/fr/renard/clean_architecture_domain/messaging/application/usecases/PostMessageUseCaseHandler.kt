package fr.renard.clean_architecture_domain.messaging.application.usecases

import fr.renard.clean_architecture_domain.socle.time.DateProvider
import fr.renard.clean_architecture_domain.messaging.domain.entity.Message
import fr.renard.clean_architecture_domain.messaging.application.ports.MessageRepository
import fr.renard.clean_architecture_domain.messaging.application.dto.PostMessageRequestDTO
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase

@UseCase
class PostMessageUseCaseHandler(
    private val messageRepository: MessageRepository,
    private val dateProvider: DateProvider,
) {
    fun handle(postMessageRequestDTO: PostMessageRequestDTO) {
        if (messageRepository.existsById(postMessageRequestDTO.id)) {
            throw RuntimeException("Message already exists")
        }

        val messageToCreate = Message(
            postMessageRequestDTO.id,
            postMessageRequestDTO.author,
            postMessageRequestDTO.text,
            dateProvider.now()
        )

        messageRepository.save(messageToCreate)
    }
}