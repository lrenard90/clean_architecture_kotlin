package fr.renard.springbootrest.web.dto

import fr.renard.clean_architecture_domain.product.model.Product

class ProductDto(val id: Long?, val name: String) {
    constructor(product: Product) : this(product.id, product.name)
}