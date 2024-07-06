package ru.example.spingdata.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import ru.example.spingdata.dto.PaymentDto
import ru.example.spingdata.service.PaymentService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/payment")
class PaymentController(
    private val paymentService: PaymentService
) {
    @PostMapping
    fun deliver(@RequestBody paymentDto: PaymentDto): Long? =
        paymentService.addPayment(paymentDto)

    @GetMapping("/{customerId}/meta")
    fun paymentByCustomerMeta(@PathVariable customerId: Long): List<PaymentDto>? =
        paymentService.getPayment(customerId)

    @GetMapping("/{customerId}")
    fun paymentByCustomer(
        @PathVariable customerId: Long, pageable: Pageable
    ): Page<PaymentDto>? =
        paymentService.getPayment(customerId, pageable)

    @DeleteMapping("/{paymentId}")
    fun deletePaymentById(@PathVariable paymentId: Long) =
        paymentService.deletePayment(paymentId)
}