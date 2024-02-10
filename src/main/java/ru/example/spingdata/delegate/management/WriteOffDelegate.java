package ru.example.spingdata.delegate.management;

import com.example.warehouseservice.dto.enums.UnitType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.ManagementUtils;
import ru.example.spingdata.aop.annotations.BusinessStep;
import ru.example.spingdata.api.WarehouseServiceApi;
import ru.example.spingdata.api.dto.WarehouseRequest;
import ru.example.spingdata.dto.CardDto;
import ru.example.spingdata.dto.CardRelationComponentDto;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WriteOffDelegate implements JavaDelegate {
    private final WarehouseServiceApi warehouseService;
    private final ManagementUtils utils;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {

        CardDto card = utils.getObject("card", CardDto.class, delegateExecution);

        BigDecimal qty = utils.getVariable("qty", BigDecimal.class, delegateExecution);

        List<CardRelationComponentDto> components = card.getComponents();

        components.forEach(
                cardRelationComponentDto -> {
                    BigDecimal requiredStock = cardRelationComponentDto.getQty().multiply(qty)
                            .subtract(cardRelationComponentDto.getComponent().getStock());
                    cardRelationComponentDto.getComponent().setStock(requiredStock);
                }
        );

        log.info("write off components {}", components);

        BigDecimal costs = BigDecimal.ZERO;
//                warehouseService.writeOff(components);

        components.forEach(cardRelationComponentDto -> {
            BigDecimal cost = warehouseService.seizeUnit(WarehouseRequest.builder()
                    .amount(cardRelationComponentDto.getQty().multiply(qty))
                    .cost(BigDecimal.ZERO)
                    .childId(cardRelationComponentDto.getComponent().getId())
                    .orderNumber("")
                    .type(UnitType.COMPONENT)
                    .build());
            costs.add(cost);
            //TODO costs = costs.add(cost);
        });

        delegateExecution.setVariable("componentsCost", costs);
        log.info("cost {}", costs);
    }
}
