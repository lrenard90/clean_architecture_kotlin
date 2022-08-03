package fr.renard.clean_architecture_domain.acceptance.product

import fr.renard.clean_architecture_domain.product.domain.Product
import fr.renard.clean_architecture_domain.product.domain.ProductCreation
import fr.renard.clean_architecture_domain.product.usecases.ManageProductUseCase
import fr.renard.clean_architecture_domain.product.usecases.port.ManageProduct
import fr.renard.clean_architecture_domain.utils.mocks.ProductRepositoryMock
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.java8.En
import org.assertj.core.api.Java6Assertions.assertThat

class ProductStepDefs: En {

    private val manageProduct: ManageProduct = ManageProductUseCase(ProductRepositoryMock())

    private var product: Product? = null

    @When("we create a product with name {string}")
    fun createProduct(name: String) {
        this.product = manageProduct.createProduct(ProductCreation(name))
    }

    @Then("the product is created with name {string}")
    fun checkCreateProduct(name: String) {
        assertThat(this.product?.id).isNotNull
        assertThat(this.product?.name).isEqualTo(name)
    }

}