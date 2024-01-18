package fr.renard.clean_architecture_application.unit.messaging.test_doubles.repository

import fr.renard.clean_architecture_application.messaging.application.ports.UserRepository
import fr.renard.clean_architecture_application.messaging.domain.entity.User

class InMemoryUserRepository: UserRepository {
    private val userMap = mutableMapOf<String, User>()

    override fun save(user: User): User {
        userMap[user.name] = user
        return user
    }

    override fun findByName(name: String): User? {
        return userMap[name]
    }

    fun get(name: String): User {
        return userMap[name] ?: throw IllegalArgumentException("User $name not found")
    }

    fun feedWith(user: User) {
        userMap[user.name] = user
    }
}