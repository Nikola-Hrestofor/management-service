package ru.example.spingdata.api;

import ru.example.spingdata.dto.CardRelationComponentDto;
import ru.example.spingdata.dto.ComponentDto;

import java.math.BigDecimal;
import java.util.List;

public interface WarehouseServiceApi {
    Boolean isPresentByComponentAndCount(ComponentDto componentDto, BigDecimal qty);

    BigDecimal writeOff(List<CardRelationComponentDto> cardRelationComponentDtos);

    void addProduction(Long id, BigDecimal qty);
}
