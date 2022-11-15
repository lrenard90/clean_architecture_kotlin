package fr.renard.springbootrest.product.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import fr.renard.springbootrest.e2e.configuration.E2eApiTest
import fr.renard.springbootrest.product.web.dto.CreateProductDto
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
internal class ProductControllerE2eTest @Autowired constructor(val mockMvc: MockMvc, val objectMapper: ObjectMapper):
    E2eApiTest() {

    private val BASE_URL = "/api/products"

    @Test
    fun `test product creation OK`() {
        mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateProductDto("Product 1"))
        }
        .andDo { print() }
        .andExpect {
            status { isOk() }
            jsonPath("$.id") {
                isNotEmpty()
            }
            jsonPath("$.name") {
                value("Product 1")
            }
        }
    }

}