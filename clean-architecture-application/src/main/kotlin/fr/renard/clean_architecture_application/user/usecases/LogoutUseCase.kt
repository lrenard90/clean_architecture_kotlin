package fr.renard.clean_architecture_application.user.usecases

import fr.renard.clean_architecture_application.user.model.UserEmail

interface LogoutUseCase {

    fun logout(email: UserEmail)

}