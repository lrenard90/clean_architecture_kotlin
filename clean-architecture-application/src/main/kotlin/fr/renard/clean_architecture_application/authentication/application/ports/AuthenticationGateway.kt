package fr.renard.clean_architecture_application.authentication.application.ports;

interface AuthenticationGateway {
    fun authenticate(username: String)
    fun logout()
    fun getCurrentLoggedUserName(): String
}
