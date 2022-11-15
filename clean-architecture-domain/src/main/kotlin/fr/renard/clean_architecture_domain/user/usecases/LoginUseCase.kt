package fr.renard.clean_architecture_domain.user.usecases

import fr.renard.clean_architecture_domain.user.model.UserEmail
import fr.renard.clean_architecture_domain.user.model.UserPassword

interface LoginUseCase {

    fun login(email: UserEmail, password: UserPassword)

}