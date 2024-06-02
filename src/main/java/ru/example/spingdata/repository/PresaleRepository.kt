package ru.example.spingdata.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.example.spingdata.entity.PresaleEntity
import java.util.UUID

interface PresaleRepository : JpaRepository<PresaleEntity, Long> {
    fun getAllByUuid(uuid: UUID) : List<PresaleEntity>
}