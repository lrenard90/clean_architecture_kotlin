package fr.renard.application_hibernate_data_provider.product.repository

import fr.renard.application_hibernate_data_provider.product.mapper.ProductMapper
import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.port.out.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductHibernateRepository(val productEntityHibernateRepository: ProductEntityHibernateRepository, val productMapper: ProductMapper) : ProductRepository {

    override fun save(product: Product): Product {
        val productEntity = productMapper.toEntity(product)
        return productMapper.toDomain(productEntityHibernateRepository.save(productEntity))
    }

}