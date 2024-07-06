package ru.example.spingdata.dto

import ru.example.spingdata.dto.enums.Direction
import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentDto(
    val id: Long,
    val customerId: Long,
    val orderNumber: String?,
    val amount: BigDecimal,
    val direction: Direction,
    val deliveryTime: LocalDateTime = LocalDateTime.now()
)
