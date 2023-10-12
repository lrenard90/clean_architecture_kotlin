package fr.renard.clean_architecture_domain.messaging.usecases

import fr.renard.clean_architecture_domain.socle.time.DateProvider
import fr.renard.clean_architecture_domain.messaging.model.entity.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import fr.renard.clean_architecture_domain.messaging.usecases.dto.PostMessageRequestDTO
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