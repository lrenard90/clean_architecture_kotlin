package fr.renard.clean_architecture_application.authentication.application.usecases

import fr.renard.clean_architecture_application.authentication.domain.UserEmail

interface LogoutUseCase {

    fun logout(email: UserEmail)

}