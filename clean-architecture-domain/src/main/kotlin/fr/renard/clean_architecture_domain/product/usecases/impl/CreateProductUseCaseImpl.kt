package fr.renard.clean_architecture_domain.product.usecases.impl

import fr.renard.clean_architecture_domain.product.ports.ProductRepository
import fr.renard.clean_architecture_domain.product.usecases.CreateProductUseCase
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationRequest
import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationResponse
import fr.renard.clean_architecture_domain.product.usecases.boundary.mapper.CreateProductUseCaseConverter
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase

@UseCase
class CreateProductUseCaseImpl(private var productRepository: ProductRepository, private val createProductUseCaseConverter: CreateProductUseCaseConverter) : CreateProductUseCase {

    override fun create(productCreationRequest: ProductCreationRequest): ProductCreationResponse {
        val productToCreate = createProductUseCaseConverter.toProduct(productCreationRequest)
        val createdProduct = productRepository.save(productToCreate)
        return createProductUseCaseConverter.toResponse(createdProduct)
    }

}