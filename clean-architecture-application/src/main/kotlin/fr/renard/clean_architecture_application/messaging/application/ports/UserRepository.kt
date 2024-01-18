package fr.renard.clean_architecture_application.messaging.application.ports

import fr.renard.clean_architecture_application.messaging.domain.entity.User


interface UserRepository {
    fun save(user: User): User
    fun findByName(currentLoggedUserName: String): User?
}
