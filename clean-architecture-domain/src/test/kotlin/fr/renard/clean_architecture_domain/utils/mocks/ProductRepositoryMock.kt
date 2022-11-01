package fr.renard.clean_architecture_domain.utils.mocks

import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.port.secondary.repository.ProductRepository

class ProductRepositoryMock : ProductRepository {

    private var products: HashMap<Long, Product> = HashMap()

    override fun save(product: Product): Product {
        val productId = computeNextId()
        product.id = productId
        products[productId] = product
        return product
    }

    private fun computeNextId() = products.size + 1L

}