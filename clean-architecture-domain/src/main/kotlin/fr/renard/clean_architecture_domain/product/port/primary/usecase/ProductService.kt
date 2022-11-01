package fr.renard.clean_architecture_domain.product.port.primary.usecase

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductCreation

interface ProductService {

    fun createProduct(productCreation: ProductCreation): Product

}