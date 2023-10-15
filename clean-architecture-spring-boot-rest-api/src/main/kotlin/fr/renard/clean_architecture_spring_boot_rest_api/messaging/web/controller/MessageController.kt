package fr.renard.clean_architecture_spring_boot_rest_api.messaging.web.controller

import fr.renard.clean_architecture_application.messaging.application.usecases.PostMessageUseCaseHandler
import fr.renard.clean_architecture_application.messaging.application.dto.PostMessageRequestDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/messages")
class MessageController(private var postMessageUseCaseHandler: PostMessageUseCaseHandler) {

    private val logger: Logger = LoggerFactory.getLogger(MessageController::class.java)

    @PostMapping
    fun postMessage(@RequestBody postMessageRequestDTO: PostMessageRequestDTO) {
        logger.debug("Post message {}", postMessageRequestDTO)
        postMessageUseCaseHandler.handle(postMessageRequestDTO)
    }

}