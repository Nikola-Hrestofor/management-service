package ru.example.spingdata.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.example.spingdata.api.TechCardServiceApi
import ru.example.spingdata.api.WarehouseServiceApi
import ru.example.spingdata.api.dto.WarehouseRequest
import ru.example.spingdata.dto.DeliveryDto
import ru.example.spingdata.dto.enums.Direction
import ru.example.spingdata.repository.DeliveryRepository
import ru.example.spingdata.repository.mapper.DeliveryMapper

@Service
class DeliveryService(
    private var deliveryRepository: DeliveryRepository,
    private var deliveryMapper: DeliveryMapper,
    private var warehouseServiceApi: WarehouseServiceApi,
    private val productService: ProductService
) {
    fun deleteDelivery(deliveryId: Long) {
        deliveryRepository.deleteById(deliveryId)
    }

    fun getDelivery(customerId: Long): List<DeliveryDto>? {
        val delivery = deliveryRepository.findByCustomerId(customerId)
        return delivery.map { val productName = productService.getProductById(it.productId)?.name
            deliveryMapper.toModel(it, productName)}
    }

    fun getDelivery(
        customerId: Long, pageable: Pageable
    ): Page<DeliveryDto>? {
        return deliveryRepository.findByCustomerId(customerId, pageable)
            .map { val productName = productService.getProductById(it.productId)?.name
                deliveryMapper.toModel(it, productName) }
    }

    fun addDelivery(deliveryDto: DeliveryDto): Long? {
        val warehouseRequest = WarehouseRequest(
            deliveryDto.qty,
            deliveryDto.price,
            deliveryDto.unitType,
            deliveryDto.productId,
            deliveryDto.orderNumber.orEmpty(),
        )
        if (deliveryDto.direction == Direction.DELIVERY) {
            warehouseServiceApi.seizeUnit(
                warehouseRequest
            )
        } else {
            warehouseServiceApi.addNewUnit(
                warehouseRequest
            )
        }

        return deliveryRepository.save(deliveryMapper.toEntity(deliveryDto)).id
    }
}