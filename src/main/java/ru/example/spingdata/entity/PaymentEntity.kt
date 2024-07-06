package ru.example.spingdata.entity

import ru.example.spingdata.dto.enums.Direction
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payment", schema = "management")
class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null;

    @Column(name = "customer_id")
    var customerId: Long = 0;

    @Column(name = "order_number")
    var orderNumber: String? = "";

    @Column(name = "amount")
    var amount: BigDecimal = BigDecimal.ZERO;

    @Column(name = "direction")
    var direction: String = Direction.DELIVERY.name;

    @Column(name = "delivery_time")
    var deliveryTime: LocalDateTime? = LocalDateTime.now()
}
