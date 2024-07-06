package ru.example.spingdata.entity

import ru.example.spingdata.api.dto.enums.UnitType
import ru.example.spingdata.dto.enums.Direction
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "delivery", schema = "management")
class DeliveryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = 0,
    @Column(name = "customer_id")
    var customerId: Long = 0,
    @Column(name = "order_number")
    var orderNumber: String? = "",
    @Column(name = "product_id")
    var productId: Long = 0,
    @Column(name = "unit_type")
    var unitType: String = UnitType.PRODUCT.name,
    @Column(name = "qty")
    var qty: BigDecimal = BigDecimal.ZERO,
    @Column(name = "price")
    var price: BigDecimal = BigDecimal.ZERO,
    @Column(name = "direction")
    var direction: String = Direction.ARRIVING.name,
    @Column(name = "delivery_time")
    var deliveryTime: LocalDateTime? = LocalDateTime.now()
)