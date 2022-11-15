package fr.renard.application_hibernate_data_provider.product.repository

import fr.renard.clean_architecture_domain.product.model.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ProductHibernateRepositoryIntegrationTest @Autowired constructor(val productHibernateRepository: ProductHibernateRepository) {

    @Autowired
    lateinit var productEntityHibernateRepository: ProductEntityHibernateRepository

    @Test
    fun `save product`() {
        val productToSave = Product("Product 1")

        val savedProduct = productHibernateRepository.save(productToSave)

        checkProductCreation(savedProduct, productToSave.name)
    }

    private fun checkProductCreation(
        savedProduct: Product,
        productName: String
    ) {
        assertThat(savedProduct.id).isNotNull;
        assertThat(savedProduct.name).isEqualTo(productName);
        assertThat(savedProduct.toSnapshot().privateAttribute).isNotNull;
    }

}