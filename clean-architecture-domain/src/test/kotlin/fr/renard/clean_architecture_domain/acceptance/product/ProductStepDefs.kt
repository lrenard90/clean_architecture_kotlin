package fr.renard.clean_architecture_domain.acceptance.product

import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.CreateProductUseCase
import fr.renard.clean_architecture_domain.product.usecases.impl.CreateProductUseCaseImpl
import fr.renard.clean_architecture_domain.acceptance.product.repository.InMemoryProductRepository
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationResponse
import fr.renard.clean_architecture_domain.product.usecases.boundary.mapper.impl.CreateProductUseCaseConverterImpl
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat

class ProductStepDefs: En {

    private val createProductUseCase: CreateProductUseCase = CreateProductUseCaseImpl(InMemoryProductRepository(), CreateProductUseCaseConverterImpl())

    private var productCreationResponse: ProductCreationResponse? = null

    @When("we create a product with name {string}")
    fun createProduct(name: String) {
        this.productCreationResponse = createProductUseCase.create(ProductCreationRequest(name))
    }

    @Then("the product is created with name {string}")
    fun checkCreateProduct(name: String) {
        assertThat(this.productCreationResponse?.id).isNotNull
        assertThat(this.productCreationResponse?.name).isEqualTo(name)
    }

}