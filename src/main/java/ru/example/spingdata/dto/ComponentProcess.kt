package ru.example.spingdata.dto

import java.util.*

data class ComponentProcess(
        val id: UUID,
        val cardName: String?,
        val cardCode: String?,
        val stepName: String?,
        val required: String?
)