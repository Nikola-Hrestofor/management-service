package ru.example.spingdata.service

import org.springframework.stereotype.Service
import ru.example.spingdata.dto.enums.Direction
import ru.example.spingdata.repository.DeliveryRepository
import ru.example.spingdata.repository.PaymentRepository
import java.math.BigDecimal

@Service
class BalanceService(
    private var deliveryRepository: DeliveryRepository,
    private var paymentRepository: PaymentRepository
) {
    fun getBalanceByCustomerId(customerId: Long): BigDecimal {
        val delivery = deliveryRepository.findByCustomerId(customerId)
        val payment = paymentRepository.findByCustomerId(customerId)

        val sumOfMoney = payment.map {
            if (Direction.valueOf(it.direction) == Direction.DELIVERY) {
                BigDecimal.ONE
            } else {
                BigDecimal.valueOf(-1)
            }.multiply(it.amount)
        }
            .sumOf { it }

        val sumOfProduct = delivery.map {
            if (Direction.valueOf(it.direction) == Direction.DELIVERY) {
                BigDecimal.ONE
            } else {
                BigDecimal.valueOf(-1)
            }.multiply(it.price)
                .multiply(it.qty)
        }
            .sumOf { it }

        return sumOfMoney.add(sumOfProduct)
    }
}