package fr.renard.application_hibernate_data_provider.product.entity

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
    var id: Long?

    @Column(name = "name")
    var name: String

    @Column(name = "private_attribute")
    var privateAttribute: String

    constructor(id: Long?, name: String, privateAttribute: String) {
        this.id = id
        this.name = name
        this.privateAttribute = privateAttribute
    }

}