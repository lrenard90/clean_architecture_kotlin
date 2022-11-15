package fr.renard.clean_architecture_domain.product.usecases.boundary.mapper

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationResponse

interface CreateProductUseCaseConverter {

    fun toProduct(productCreationRequest: ProductCreationRequest): Product
    fun toResponse(product: Product): ProductCreationResponse

}