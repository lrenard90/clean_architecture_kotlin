package fr.renard.clean_architecture_application.authentication.application.usecases

import fr.renard.clean_architecture_application.authentication.domain.UserEmail
import fr.renard.clean_architecture_application.authentication.domain.UserPassword

interface LoginUseCase {

    fun login(email: UserEmail, password: UserPassword)

}