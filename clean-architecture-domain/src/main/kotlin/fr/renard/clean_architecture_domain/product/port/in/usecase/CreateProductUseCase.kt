package fr.renard.clean_architecture_domain.product.port.`in`.usecase

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductCreation

interface CreateProductUseCase {

    fun createProduct(productCreation: ProductCreation): Product

}