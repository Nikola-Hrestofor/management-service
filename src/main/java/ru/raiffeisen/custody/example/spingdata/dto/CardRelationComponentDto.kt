package com.example.techcardservice.dto

import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto
//import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto
import java.math.BigDecimal

data class CardRelationComponentDto(
    val id: Long,
    var component: ComponentDto,
    val qty: BigDecimal
)
