package fr.renard.clean_architecture_application.messaging.application.usecases

import fr.renard.clean_architecture_application.authentication.application.ports.AuthenticationGateway
import fr.renard.clean_architecture_application.messaging.application.dto.FollowUserRequestDTO
import fr.renard.clean_architecture_application.messaging.application.ports.UserRepository
import fr.renard.clean_architecture_application.messaging.domain.entity.User

class FollowUserUseCaseHandler(private val authenticationGateway: AuthenticationGateway, private val userRepository: UserRepository) {
    fun handle(followUserRequestDTO: FollowUserRequestDTO) {
        val currentLoggedUserName: String = authenticationGateway.getCurrentLoggedUserName()
        val existingUser: User = userRepository.findByName(currentLoggedUserName)
                ?: throw IllegalArgumentException("User $currentLoggedUserName not found")

        existingUser.follows(followUserRequestDTO.userToFollow)

        userRepository.save(existingUser)
    }
}
