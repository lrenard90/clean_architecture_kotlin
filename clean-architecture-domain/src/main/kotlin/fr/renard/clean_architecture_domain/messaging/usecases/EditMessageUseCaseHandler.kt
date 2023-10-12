package fr.renard.clean_architecture_domain.messaging.usecases

import fr.renard.clean_architecture_domain.messaging.model.Message
import fr.renard.clean_architecture_domain.messaging.ports.MessageRepository
import fr.renard.clean_architecture_domain.messaging.usecases.dto.EditMessageRequestDTO

class EditMessageUseCaseHandler(private val messageRepository: MessageRepository) {
    fun handle(editMessageRequestDTO: EditMessageRequestDTO) {
        val messageToEdit: Message = messageRepository.findById(editMessageRequestDTO.messageId)
            .orElseThrow { RuntimeException("Message not found") }

        messageToEdit.updateText(editMessageRequestDTO.text)

        messageRepository.save(messageToEdit)
    }
}
