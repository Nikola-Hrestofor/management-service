package ru.example.spingdata.dto

import ru.example.spingdata.api.dto.enums.UnitType
import ru.example.spingdata.dto.enums.Direction
import java.math.BigDecimal
import java.time.LocalDateTime

data class DeliveryDto(
    val id: Long,
    val customerId: Long,
    val orderNumber: String?,
    val productId: Long,
    val unitType: UnitType,
    val qty: BigDecimal,
    val price: BigDecimal,
    val direction: Direction,
    val deliveryTime: LocalDateTime? = LocalDateTime.now(),
    val productName: String
)
