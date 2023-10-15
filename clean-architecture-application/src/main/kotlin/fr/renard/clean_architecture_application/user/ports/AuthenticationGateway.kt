package fr.renard.clean_architecture_application.user.ports;

interface AuthenticationGateway {

    fun authenticate(email: String, password: String)

    fun logout(email: String)

}
