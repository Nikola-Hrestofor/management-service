package ru.raiffeisen.custody.example.spingdata.api.stubImpl;

import com.example.techcardservice.dto.CardRelationComponentDto;
import lombok.extern.slf4j.Slf4j;
import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto;
import org.springframework.stereotype.Service;
import ru.raiffeisen.custody.example.spingdata.api.WarehouseServiceApi;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class WarehouseServiceImpl implements WarehouseServiceApi {
    @Override
    public Boolean isPresentByComponentAndCount(ComponentDto componentDto, BigDecimal qty) {
        return false;
    }

    @Override
    public BigDecimal writeOff(List<CardRelationComponentDto> cardRelationComponentDtos) {
        return BigDecimal.valueOf(3456);
    }

    @Override
    public void addProduction(Long id, BigDecimal qty) {
        log.info("Учтено на складе {} штук продукции {}", qty, id);
    }
}
