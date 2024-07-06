package ru.example.spingdata.repository.mapper;

import org.mapstruct.Mapper;
import ru.example.spingdata.dto.PaymentDto;
import ru.example.spingdata.entity.PaymentEntity;

import java.util.List;

@Mapper
public interface PaymentMapper {
    PaymentEntity toEntity(PaymentDto model);

    PaymentDto toModel(PaymentEntity entity);

    List<PaymentDto> toModels(List<PaymentEntity> entities);
}
