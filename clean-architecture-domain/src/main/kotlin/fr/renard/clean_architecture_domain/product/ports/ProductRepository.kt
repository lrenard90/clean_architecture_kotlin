package fr.renard.clean_architecture_domain.product.ports

import fr.renard.clean_architecture_domain.product.model.Product

interface ProductRepository {

    fun save(product: Product): Product

}