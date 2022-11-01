package fr.renard.clean_architecture_domain.product.model

data class ProductCreation(val nom: String) {
    fun toProduct(): Product {
        return Product(nom)
    }
}
