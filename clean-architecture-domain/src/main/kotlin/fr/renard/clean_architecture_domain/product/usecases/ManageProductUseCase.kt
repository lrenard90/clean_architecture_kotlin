package fr.renard.clean_architecture_domain.product.usecases

import fr.renard.clean_architecture_domain.product.domain.Product
import fr.renard.clean_architecture_domain.product.domain.ProductCreation
import fr.renard.clean_architecture_domain.product.domain.repository.ProductRepository
import fr.renard.clean_architecture_domain.product.usecases.port.ManageProduct

@UseCase
class ManageProductUseCase(private var productRepository: ProductRepository) : ManageProduct {

    override fun createProduct(productCreation: ProductCreation): Product {
        val productToCreate = productCreation.toProduct()
        return productRepository.save(productToCreate)
    }

}