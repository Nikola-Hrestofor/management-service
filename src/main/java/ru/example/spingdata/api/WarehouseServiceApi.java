package ru.example.spingdata.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.example.spingdata.api.dto.WarehouseRequest;
import ru.example.spingdata.dto.CardRelationComponentDto;
import ru.example.spingdata.dto.ComponentDto;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "warehouse", path = "/api/v1/warehouse", url = "${spring.services.warehouse.host}")
public interface WarehouseServiceApi {
    @PostMapping("/add")
    BigDecimal addNewUnit(@RequestBody WarehouseRequest warehouseRequest);

    @PostMapping("/seize")
    BigDecimal seizeUnit(@RequestBody WarehouseRequest warehouseRequest);
}
