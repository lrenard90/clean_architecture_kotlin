package fr.renard.application_hibernate_data_provider.product.entity

import fr.renard.clean_architecture_domain.product.model.Product
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductEntity {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "product_id_sequence"
    )
    @SequenceGenerator(
        name = "product_id_sequence",
        sequenceName = "product_id_sequence",
        allocationSize = 1
    )
    private var id: Long?

    @Column(name = "name")
    private var name: String

    constructor(product: Product) {
        this.id = product.id
        this.name = product.name
    }

}