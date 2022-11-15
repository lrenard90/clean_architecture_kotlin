package fr.renard.springbootrest.product.web.dto

import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest

class CreateProductDto(val name: String) {
    fun toProductCreation(): ProductCreationRequest {
        return ProductCreationRequest(name)
    }

    override fun toString(): String {
        return "CreateProductDto(name='$name')"
    }
}