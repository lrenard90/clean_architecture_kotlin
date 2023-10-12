package fr.renard.clean_architecture_domain.messaging.usecases.dto

import java.util.*

data class EditMessageRequestDTO(val messageId: UUID, val text: String) {

}
