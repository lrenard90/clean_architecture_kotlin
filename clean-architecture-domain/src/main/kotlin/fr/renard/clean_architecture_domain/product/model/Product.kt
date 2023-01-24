package fr.renard.clean_architecture_domain.product.model

import fr.renard.clean_architecture_domain.socle.model.ModelSnapshot

class Product(var name: String): ModelSnapshot<ProductState> {
    var id: Long? = null
    private var privateAttribute: String = "private"

    init {
        if (this.name.isBlank()) {
            throw IllegalArgumentException("Name cannot be blank");
        }
    }

    constructor(state: ProductState): this(state.name) {
        this.id = state.id
        this.privateAttribute = state.privateAttribute
    }

    override fun toSnapshot(): ProductState {
        return ProductState(id, name, privateAttribute)
    }
}