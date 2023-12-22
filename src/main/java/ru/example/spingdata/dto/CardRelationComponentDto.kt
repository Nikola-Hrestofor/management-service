package ru.example.spingdata.dto

import java.math.BigDecimal

data class CardRelationComponentDto(
        val id: Long,
        var component: ComponentDto,
        val qty: BigDecimal
)
