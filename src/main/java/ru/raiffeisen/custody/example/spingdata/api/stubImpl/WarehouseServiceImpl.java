package ru.raiffeisen.custody.example.spingdata.api.stubImpl;

import com.example.techcardservice.dto.CardRelationComponentDto;
import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto;
import org.springframework.stereotype.Service;
import ru.raiffeisen.custody.example.spingdata.api.WarehouseServiceApi;

import java.math.BigDecimal;
import java.util.List;

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
}
