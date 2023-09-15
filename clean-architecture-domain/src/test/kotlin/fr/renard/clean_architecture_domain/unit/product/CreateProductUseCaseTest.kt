package fr.renard.clean_architecture_domain.unit.product

import fr.renard.clean_architecture_domain.product.usecases.CreateProductUseCase
import fr.renard.clean_architecture_domain.shared.repository.InMemoryProductRepository
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.boundary.mapper.impl.CreateProductUseCaseConverterImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreateProductUseCaseTest {

    private val createProductUseCase = CreateProductUseCase(InMemoryProductRepository(), CreateProductUseCaseConverterImpl());

    @Test
    fun `Create a product`() {
        val productName = "Product"

        val createdProduct = createProductUseCase.handle(ProductCreationRequest(productName))

        assertNotNull(createdProduct.id)
        assertEquals(productName, createdProduct.name)
    }

    @Test
    fun `Create a product request should specify a not empty name`() {
        assertThrows<RuntimeException> {
            createProductUseCase.handle(ProductCreationRequest(""))
        }
    }

}