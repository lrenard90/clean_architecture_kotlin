package fr.renard.clean_architecture_domain.product.domain.repository

import fr.renard.clean_architecture_domain.product.domain.Product

interface ProductRepository {

    fun save(product: Product): Product

}