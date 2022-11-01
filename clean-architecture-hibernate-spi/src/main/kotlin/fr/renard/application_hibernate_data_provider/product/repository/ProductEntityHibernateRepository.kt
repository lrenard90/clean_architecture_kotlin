package fr.renard.application_hibernate_data_provider.product.repository

import fr.renard.application_hibernate_data_provider.product.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductEntityHibernateRepository: JpaRepository<ProductEntity, Long> {
}