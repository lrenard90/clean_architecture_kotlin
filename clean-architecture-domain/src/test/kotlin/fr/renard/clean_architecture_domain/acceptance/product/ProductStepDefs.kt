package fr.renard.clean_architecture_domain.acceptance.product

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductCreation
import fr.renard.clean_architecture_domain.product.port.`in`.usecase.CreateProductUseCase
import fr.renard.clean_architecture_domain.product.usecases.CreateProductUseCaseImpl
import fr.renard.clean_architecture_domain.acceptance.product.repository.InMemoryProductRepository
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat

class ProductStepDefs: En {

    private val createProductUseCase: CreateProductUseCase = CreateProductUseCaseImpl(InMemoryProductRepository())

    private var product: Product? = null

    @When("we create a product with name {string}")
    fun createProduct(name: String) {
        this.product = createProductUseCase.create(ProductCreation(name))
    }

    @Then("the product is created with name {string}")
    fun checkCreateProduct(name: String) {
        assertThat(this.product?.id).isNotNull
        assertThat(this.product?.name).isEqualTo(name)
    }

}