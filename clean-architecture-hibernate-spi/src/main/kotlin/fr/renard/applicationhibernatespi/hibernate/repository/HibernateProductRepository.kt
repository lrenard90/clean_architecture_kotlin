package fr.renard.applicationhibernatespi.hibernate.repository

import fr.renard.clean_architecture_domain.product.domain.Product
import fr.renard.clean_architecture_domain.product.domain.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class HibernateProductRepository: ProductRepository {
    override fun save(product: Product): Product {
        TODO("Not yet implemented")
    }
}