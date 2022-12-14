package fr.renard.springbootrest.e2e.product

import fr.renard.springbootrest.product.web.dto.CreateProductDto
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.java8.En
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.springframework.boot.web.server.LocalServerPort

class ProductStepDefs : En {

    private val BASE_URL = "/api/products"

    private var createProductResponse: ValidatableResponse? = null

    @When("we create a product with name {string}")
    fun createProduct(name: String) {
        createProductResponse = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(CreateProductDto(name))
            .post(BASE_URL)
            .then()
    }

    @Then("the product is created with name {string}")
    fun checkCreateProduct(name: String) {
        this.createProductResponse!!.assertThat()
            .body(equalTo("{\"id\":1,\"name\":\"Product 01\"}"))
    }

}