package ru.example.spingdata.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.example.spingdata.entity.ProductEntity

interface ProductRepository : JpaRepository<ProductEntity, Long> {
}