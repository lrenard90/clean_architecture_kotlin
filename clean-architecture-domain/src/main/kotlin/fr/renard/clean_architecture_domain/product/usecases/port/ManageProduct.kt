package fr.renard.clean_architecture_domain.product.usecases.port

import fr.renard.clean_architecture_domain.product.domain.Product
import fr.renard.clean_architecture_domain.product.domain.ProductCreation

interface ManageProduct {

    fun createProduct(productCreation: ProductCreation): Product

}