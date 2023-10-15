package fr.renard.clean_architecture_application.authentication.application.ports

import fr.renard.clean_architecture_application.authentication.domain.User

interface UserRepository {

    fun save(user: User): User

}