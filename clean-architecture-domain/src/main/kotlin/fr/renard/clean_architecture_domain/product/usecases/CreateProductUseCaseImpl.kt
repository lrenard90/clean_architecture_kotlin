package fr.renard.clean_architecture_domain.product.usecases

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductCreation
import fr.renard.clean_architecture_domain.product.port.`in`.usecase.CreateProductUseCase
import fr.renard.clean_architecture_domain.product.port.out.repository.ProductRepository
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase

@UseCase
class CreateProductUseCaseImpl(private var productRepository: ProductRepository) : CreateProductUseCase {

    override fun create(productCreation: ProductCreation): Product {
        val productToCreate = productCreation.toProduct()
        return productRepository.save(productToCreate)
    }

}