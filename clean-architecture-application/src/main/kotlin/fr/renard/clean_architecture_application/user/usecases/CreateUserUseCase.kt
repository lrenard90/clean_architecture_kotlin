package fr.renard.clean_architecture_application.user.usecases

import fr.renard.clean_architecture_application.user.model.UserEmail
import fr.renard.clean_architecture_application.user.model.UserPassword

interface CreateUserUseCase {

    fun create(email: UserEmail, password: UserPassword)

}