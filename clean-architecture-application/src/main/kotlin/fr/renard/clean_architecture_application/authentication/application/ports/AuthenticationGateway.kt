package fr.renard.clean_architecture_application.authentication.application.ports;

// TODO define authentication port in which BC ? messaging or authentication ?
interface AuthenticationGateway {
    fun authenticate(email: String, password: String)
    fun logout(email: String)
}
