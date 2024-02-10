package ru.example.spingdata.dto

import lombok.Setter
import java.math.BigDecimal

@Setter
data class ComponentDto(
        val id: Long?,
        val name: String,
        val unit: String,
        val category: CategoryDto,
        val code: String,
        var stock: BigDecimal,
)
