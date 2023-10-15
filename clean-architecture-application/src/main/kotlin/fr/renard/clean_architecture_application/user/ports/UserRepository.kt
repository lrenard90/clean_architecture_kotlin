package fr.renard.clean_architecture_application.user.ports

import fr.renard.clean_architecture_application.user.model.User

interface UserRepository {

    fun save(user: User): User

}