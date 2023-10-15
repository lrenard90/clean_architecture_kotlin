package fr.renard.clean_architecture_application.messaging.domain.value_object

data class MessageText(val value: String) {
    init {
        if (value.isBlank()) throw IllegalArgumentException("Message text must not be blank")
        if (value.length > 280) throw IllegalArgumentException("Message text must be less than 280 characters")
    }
}