package fr.renard.clean_architecture_domain.user.ports

import fr.renard.clean_architecture_domain.user.model.User

interface UserRepository {

    fun save(user: User): User

}