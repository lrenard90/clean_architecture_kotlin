package fr.renard.clean_architecture_domain.product.usecases

import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationResponse

interface CreateProductUseCase {

    fun create(productCreationRequest: ProductCreationRequest): ProductCreationResponse

}