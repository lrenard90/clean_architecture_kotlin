package fr.renard.clean_architecture_domain.unit.product

import fr.renard.clean_architecture_domain.shared.repository.InMemoryProductRepository
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.boundary.mapper.impl.CreateProductUseCaseConverterImpl
import fr.renard.clean_architecture_domain.product.usecases.impl.CreateProductUseCaseImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreateProductUseCaseTest {

    private val createProductUseCase = CreateProductUseCaseImpl(InMemoryProductRepository(), CreateProductUseCaseConverterImpl());

    @Test
    fun `Create a product`() {
        val productName = "Product"

        val createdProduct = createProductUseCase.create(ProductCreationRequest(productName))

        assertNotNull(createdProduct.id)
        assertEquals(productName, createdProduct.name)
    }

    @Test
    fun `Create a product request should specify a not empty name`() {
        assertThrows<RuntimeException> {
            createProductUseCase.create(ProductCreationRequest(""))
        }
    }

}