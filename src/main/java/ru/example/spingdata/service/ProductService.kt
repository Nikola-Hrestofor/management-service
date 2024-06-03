package ru.example.spingdata.service

import org.springframework.stereotype.Service
import ru.example.spingdata.dto.ProductDto
import ru.example.spingdata.repository.ProductRepository

@Service
class ProductService(val productRepository: ProductRepository) {
    fun getProductById(id: Long) : ProductDto? =
        productRepository.getById(id).let { it.id?.let { it1 -> ProductDto(it1, it.uuid, it.name) } }
}