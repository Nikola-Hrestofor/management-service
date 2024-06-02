package ru.example.spingdata.api.dto;

import ru.example.spingdata.api.dto.enums.UnitType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WarehouseRequest {
    BigDecimal amount;
    BigDecimal cost;
    UnitType type;
    Long childId;
    String orderNumber;
}
