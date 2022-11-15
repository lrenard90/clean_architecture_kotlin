package fr.renard.springbootrest.product.web.controller

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.port.`in`.usecase.CreateProductUseCase
import fr.renard.springbootrest.product.web.dto.CreateProductDto
import fr.renard.springbootrest.product.web.dto.ProductDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(private var createProductUseCase: CreateProductUseCase) {

    private val logger: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @PostMapping
    fun createProduct(@RequestBody createProductDto: CreateProductDto): ProductDto {
        logger.debug("Creation of product {}", createProductDto)
        val createdProduct: Product = createProductUseCase.create(createProductDto.toProductCreation())
        return ProductDto(createdProduct);
    }

}