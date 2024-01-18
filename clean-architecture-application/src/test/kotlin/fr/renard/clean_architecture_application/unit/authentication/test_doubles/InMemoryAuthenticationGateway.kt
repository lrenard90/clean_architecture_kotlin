package fr.renard.clean_architecture_application.unit.authentication.test_doubles

import fr.renard.clean_architecture_application.authentication.application.ports.AuthenticationGateway

class InMemoryAuthenticationGateway: AuthenticationGateway {
    private var currentLoggedUser: String? = null

    override fun authenticate(userName: String) {
        currentLoggedUser = userName
    }

    override fun logout() {
        currentLoggedUser = null
    }

    override fun getCurrentLoggedUserName(): String {
        return currentLoggedUser!!
    }
}