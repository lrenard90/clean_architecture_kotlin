package fr.renard.springbootrest.product.web.dto

import fr.renard.clean_architecture_domain.product.model.ProductCreation

class CreateProductDto(val name: String) {
    fun toProductCreation(): ProductCreation {
        return ProductCreation(name)
    }

    override fun toString(): String {
        return "CreateProductDto(name='$name')"
    }
}