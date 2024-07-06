package ru.example.spingdata.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import ru.example.spingdata.entity.PaymentEntity

interface PaymentRepository : PagingAndSortingRepository<PaymentEntity, Long>, JpaRepository<PaymentEntity, Long> {
    fun findByCustomerId(customerId: Long): List<PaymentEntity>
    fun findByCustomerId(customerId: Long, pageable: Pageable): Page<PaymentEntity>
}