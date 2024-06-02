package ru.example.spingdata.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product", schema = "management")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = 0,

    var uuid: UUID = UUID.randomUUID(),

    var name: String = "",
)