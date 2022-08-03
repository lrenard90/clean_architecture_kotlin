package fr.renard.springbootrest.web.dto

import fr.renard.clean_architecture_domain.product.domain.ProductCreation

class CreateProductDto(val name: String) {
    fun toProductCreation(): ProductCreation {
        return ProductCreation(name)
    }
}