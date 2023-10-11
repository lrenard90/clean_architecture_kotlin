package fr.renard.springbootrest.messaging.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import fr.renard.clean_architecture_domain.messaging.usecases.dto.PostMessageRequestDTO
import fr.renard.springbootrest.e2e.configuration.E2eApiTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.*

@AutoConfigureMockMvc
internal class MessageControllerE2ETest @Autowired constructor(val mockMvc: MockMvc, val objectMapper: ObjectMapper) :
    E2eApiTest() {

    private val BASE_URL = "/api/messages"

    @Test
    fun `POST a message OK`() {
        val postMessageRequestDTO = PostMessageRequestDTO(
            UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
            "Alice",
            "Hello world!"
        )

        mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(postMessageRequestDTO)
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
            }
    }

}