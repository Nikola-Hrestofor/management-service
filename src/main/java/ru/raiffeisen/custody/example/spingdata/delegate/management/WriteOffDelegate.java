package ru.raiffeisen.custody.example.spingdata.delegate.management;

import ru.raiffeisen.custody.example.spingdata.dto.CardDto;
import ru.raiffeisen.custody.example.spingdata.dto.CardRelationComponentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.ManagementUtils;
import ru.raiffeisen.custody.example.spingdata.aop.annotations.BusinessStep;
import ru.raiffeisen.custody.example.spingdata.api.WarehouseServiceApi;

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

        BigDecimal cost = warehouseService.writeOff(components);

        delegateExecution.setVariable("componentsCost", cost);
        log.info("cost {}", cost);
    }
}
