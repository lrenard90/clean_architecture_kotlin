package fr.renard.clean_architecture_domain.acceptance.product

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductCreation
import fr.renard.clean_architecture_domain.product.port.primary.usecase.ProductService
import fr.renard.clean_architecture_domain.product.usecases.ProductServiceImpl
import fr.renard.clean_architecture_domain.utils.mocks.ProductRepositoryMock
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.java8.En
import org.assertj.core.api.Java6Assertions.assertThat

class ProductStepDefs: En {

    private val productService: ProductService = ProductServiceImpl(ProductRepositoryMock())

    private var product: Product? = null

    @When("we create a product with name {string}")
    fun createProduct(name: String) {
        this.product = productService.createProduct(ProductCreation(name))
    }

    @Then("the product is created with name {string}")
    fun checkCreateProduct(name: String) {
        assertThat(this.product?.id).isNotNull
        assertThat(this.product?.name).isEqualTo(name)
    }

}