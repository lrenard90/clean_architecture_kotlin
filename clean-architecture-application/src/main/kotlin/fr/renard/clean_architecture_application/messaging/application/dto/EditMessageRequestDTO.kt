package fr.renard.clean_architecture_application.messaging.application.dto

import java.util.*

data class EditMessageRequestDTO(val messageId: UUID, val text: String) {

}
