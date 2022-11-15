package fr.renard.application_hibernate_data_provider.product.mapper

import fr.renard.application_hibernate_data_provider.product.entity.ProductEntity
import fr.renard.clean_architecture_domain.product.model.Product
import fr.renard.clean_architecture_domain.product.model.ProductState
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface ProductEntityMapper {

    fun toEntity(product: ProductState): ProductEntity

    fun toState(product: ProductEntity): ProductState

}