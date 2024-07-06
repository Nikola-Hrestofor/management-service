package ru.example.spingdata.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.example.spingdata.api.dto.enums.UnitType;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
public class WarehouseRequest {
    BigDecimal amount;
    BigDecimal cost;
    UnitType type;
    Long childId;
    String orderNumber;

    public WarehouseRequest(BigDecimal amount, BigDecimal cost, UnitType type, Long childId, String orderNumber) {
        this.amount = amount;
        this.cost = cost;
        this.type = type;
        this.childId = childId;
        this.orderNumber = orderNumber;
    }
}

