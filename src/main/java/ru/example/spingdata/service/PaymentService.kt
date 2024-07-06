package ru.example.spingdata.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.example.spingdata.dto.PaymentDto
import ru.example.spingdata.repository.PaymentRepository
import ru.example.spingdata.repository.mapper.PaymentMapper

@Service
class PaymentService(
    private var paymentRepository: PaymentRepository,
    private var paymentMapper: PaymentMapper,
) {
    fun deletePayment(paymentId: Long) {
        paymentRepository.deleteById(paymentId)
    }

    fun getPayment(customerId: Long): List<PaymentDto>? {
        return paymentMapper.toModels(paymentRepository.findByCustomerId(customerId))
    }

    fun getPayment(
        customerId: Long, pageable: Pageable
    ): Page<PaymentDto>? {
        return paymentRepository.findByCustomerId(customerId, pageable)
            .map { deliveryEntity -> paymentMapper.toModel(deliveryEntity) }
    }

    fun addPayment(paymentDto: PaymentDto): Long? {
        return paymentRepository.save(paymentMapper.toEntity(paymentDto)).id
    }
}