package fr.renard.application_hibernate_data_provider.product.repository

import fr.renard.application_hibernate_data_provider.product.entity.ProductEntity
import fr.renard.application_hibernate_data_provider.product.mapper.ProductEntityMapper
import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.ports.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductHibernateRepository(val productEntityHibernateRepository: ProductEntityHibernateRepository, val productEntityMapper: ProductEntityMapper) :
    ProductRepository {

    override fun save(product: Product): Product {
        val productEntity: ProductEntity = productEntityMapper.toEntity(product.toSnapshot())
        val updatedProduct: ProductEntity = productEntityHibernateRepository.save(productEntity)
        return Product(productEntityMapper.toState(updatedProduct))
    }

}