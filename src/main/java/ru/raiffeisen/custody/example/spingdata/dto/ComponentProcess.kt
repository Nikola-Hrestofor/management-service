package ru.raiffeisen.custody.example.spingdata.dto

import java.util.UUID

data class ComponentProcess(
    val id : UUID,
    val cardName : String,
    val cardCode: String,
    val stepName: String?
)