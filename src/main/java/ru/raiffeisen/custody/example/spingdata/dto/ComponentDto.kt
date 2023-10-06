package ru.raiffeisen.custody.example.spingdata.dto

import com.example.techcardservice.dto.CategoryDto
import lombok.Setter
import java.math.BigDecimal

@Setter
data class ComponentDto(
    val id: Long?,
    val name: String,
    val unit: String,
    val category: CategoryDto,
    val code: String,
    var stock: BigDecimal = BigDecimal(75)
)
