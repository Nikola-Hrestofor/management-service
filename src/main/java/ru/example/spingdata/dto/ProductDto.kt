package ru.example.spingdata.dto

import java.util.UUID

data class ProductDto(
    val id: Long,
    val uuid: UUID,
    val name: String
)
