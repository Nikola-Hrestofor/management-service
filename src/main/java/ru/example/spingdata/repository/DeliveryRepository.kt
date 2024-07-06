package ru.example.spingdata.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import ru.example.spingdata.entity.DeliveryEntity

interface DeliveryRepository: PagingAndSortingRepository<DeliveryEntity, Long>, JpaRepository<DeliveryEntity, Long> {
    fun findByCustomerId(customerId: Long): List<DeliveryEntity>
    fun findByCustomerId(customerId: Long, pageable: Pageable): Page<DeliveryEntity>
}