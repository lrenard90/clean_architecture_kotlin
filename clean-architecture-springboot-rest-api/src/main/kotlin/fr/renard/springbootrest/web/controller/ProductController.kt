package fr.renard.springbootrest.web.controller

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.port.primary.usecase.ProductService
import fr.renard.springbootrest.web.dto.CreateProductDto
import fr.renard.springbootrest.web.dto.ProductDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(private var productService: ProductService) {

    @PostMapping
    fun createProduct(@RequestBody createProductDto: CreateProductDto): ProductDto {
        val createdProduct: Product = productService.createProduct(createProductDto.toProductCreation())
        return ProductDto(createdProduct);
    }

}