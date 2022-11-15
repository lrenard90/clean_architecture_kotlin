package fr.renard.springbootrest.product.web.dto

import fr.renard.clean_architecture_domain.product.usecases.boundary.dto.ProductCreationResponse

class ProductDto(val id: Long?, val name: String) {
    constructor(product: ProductCreationResponse) : this(product.id, product.name)
}