package ru.raiffeisen.custody.example.spingdata.api;

import com.example.techcardservice.dto.CardRelationComponentDto;
import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto;

import java.math.BigDecimal;
import java.util.List;

public interface WarehouseServiceApi {
    Boolean isPresentByComponentAndCount(ComponentDto componentDto, BigDecimal qty);

    BigDecimal writeOff(List<CardRelationComponentDto> cardRelationComponentDtos);
}
