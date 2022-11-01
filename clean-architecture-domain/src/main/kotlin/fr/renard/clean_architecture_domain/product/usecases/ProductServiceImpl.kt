package fr.renard.clean_architecture_domain.product.usecases

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductCreation
import fr.renard.clean_architecture_domain.product.port.primary.usecase.ProductService
import fr.renard.clean_architecture_domain.product.port.secondary.repository.ProductRepository
import fr.renard.clean_architecture_domain.socle.dependency_injection.annotation.UseCase

@UseCase
class ProductServiceImpl(private var productRepository: ProductRepository) : ProductService {

    override fun createProduct(productCreation: ProductCreation): Product {
        val productToCreate = productCreation.toProduct()
        return productRepository.save(productToCreate)
    }

}