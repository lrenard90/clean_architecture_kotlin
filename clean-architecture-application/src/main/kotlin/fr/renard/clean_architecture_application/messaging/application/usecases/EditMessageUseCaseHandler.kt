package fr.renard.clean_architecture_application.messaging.application.usecases

import fr.renard.clean_architecture_application.messaging.domain.entity.Message
import fr.renard.clean_architecture_application.messaging.application.ports.MessageRepository
import fr.renard.clean_architecture_application.messaging.application.dto.EditMessageRequestDTO

class EditMessageUseCaseHandler(private val messageRepository: MessageRepository) {
    fun handle(editMessageRequestDTO: EditMessageRequestDTO) {
        val messageToEdit: Message = messageRepository.findById(editMessageRequestDTO.messageId)
            .orElseThrow { RuntimeException("Message not found") }

        messageToEdit.editText(editMessageRequestDTO.text)

        messageRepository.save(messageToEdit)
    }
}
