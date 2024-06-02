package ru.example.spingdata.dto

import ru.example.spingdata.api.dto.enums.UnitType
import java.math.BigDecimal

data class PresaleItem(val id: Long, val type: UnitType, val qty: BigDecimal)
