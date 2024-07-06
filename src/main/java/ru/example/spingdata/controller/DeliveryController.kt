package ru.example.spingdata.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import ru.example.spingdata.dto.DeliveryDto
import ru.example.spingdata.service.DeliveryService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/delivery")
class DeliveryController(
    private val deliveryService: DeliveryService
) {
    @PostMapping
    fun deliver(@RequestBody deliveryDto: DeliveryDto): Long? =
        deliveryService.addDelivery(deliveryDto)

    @GetMapping("/{customerId}/meta")
    fun deliverByCustomerMeta(@PathVariable customerId: Long): List<DeliveryDto>? =
        deliveryService.getDelivery(customerId)

    @GetMapping("/{customerId}")
    fun deliverByCustomer(@PathVariable customerId: Long, pageable: Pageable
    ): Page<DeliveryDto>? =
        deliveryService.getDelivery(customerId, pageable)

    @DeleteMapping("/{deliveryId}")
    fun deleteDeliverById(@PathVariable deliveryId: Long) =
        deliveryService.deleteDelivery(deliveryId)
}