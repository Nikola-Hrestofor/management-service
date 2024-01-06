package ru.example.spingdata.api.stubImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.example.spingdata.api.WarehouseServiceApi;
import ru.example.spingdata.dto.CardRelationComponentDto;
import ru.example.spingdata.dto.ComponentDto;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class WarehouseServiceImpl {
    public Boolean isPresentByComponentAndCount(ComponentDto componentDto, BigDecimal qty) {
        return false;
    }

    public BigDecimal writeOff(List<CardRelationComponentDto> cardRelationComponentDtos) {
        return BigDecimal.valueOf(3456);
    }

    public void addProduction(Long id, BigDecimal qty) {
        log.info("Учтено на складе {} штук продукции {}", qty, id);
    }
}
