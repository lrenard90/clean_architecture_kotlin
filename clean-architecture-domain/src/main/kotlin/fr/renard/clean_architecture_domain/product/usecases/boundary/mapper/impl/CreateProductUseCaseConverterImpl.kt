package fr.renard.clean_architecture_domain.product.usecases.boundary.mapper.impl

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationResponse
import fr.renard.clean_architecture_domain.product.usecases.boundary.mapper.CreateProductUseCaseConverter
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.Mapper

@Mapper
class CreateProductUseCaseConverterImpl: CreateProductUseCaseConverter {
    override fun toProduct(productCreationRequest: ProductCreationRequest): Product {
        return Product(productCreationRequest.nom)
    }

    override fun toResponse(product: Product): ProductCreationResponse {
        return ProductCreationResponse(product.id!!, product.name)
    }
}