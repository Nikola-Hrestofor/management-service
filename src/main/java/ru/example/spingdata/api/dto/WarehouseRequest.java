package ru.example.spingdata.api.dto;

import com.example.warehouseservice.dto.enums.UnitType;
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
