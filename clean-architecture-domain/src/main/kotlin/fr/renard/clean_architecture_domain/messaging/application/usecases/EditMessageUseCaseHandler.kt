package fr.renard.clean_architecture_domain.messaging.application.usecases

import fr.renard.clean_architecture_domain.messaging.domain.entity.Message
import fr.renard.clean_architecture_domain.messaging.application.ports.MessageRepository
import fr.renard.clean_architecture_domain.messaging.application.dto.EditMessageRequestDTO

class EditMessageUseCaseHandler(private val messageRepository: MessageRepository) {
    fun handle(editMessageRequestDTO: EditMessageRequestDTO) {
        val messageToEdit: Message = messageRepository.findById(editMessageRequestDTO.messageId)
            .orElseThrow { RuntimeException("Message not found") }

        messageToEdit.editText(editMessageRequestDTO.text)

        messageRepository.save(messageToEdit)
    }
}
