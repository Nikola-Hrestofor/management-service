package ru.example.spingdata.repository.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.example.spingdata.dto.PresaleItem
import ru.example.spingdata.entity.PresaleEntity
import java.util.UUID

@Mapper(componentModel = "spring")
interface PresaleMapper {
    fun toPresaleEntity(dto: PresaleItem): PresaleEntity

    @Mapping(target = "uuid", source = "uuid")
    fun toPresaleEntities(dto: List<PresaleItem>): List<PresaleEntity>
}