package ru.example.spingdata.repository.mapper;

import org.mapstruct.Mapper;
import ru.example.spingdata.dto.DeliveryDto;
import ru.example.spingdata.entity.DeliveryEntity;

import java.util.List;

@Mapper
public interface DeliveryMapper {
    DeliveryEntity toEntity(DeliveryDto model);

    DeliveryDto toModel(DeliveryEntity entity, String productName);

    List<DeliveryDto> toModels(List<DeliveryEntity> entities);
}
