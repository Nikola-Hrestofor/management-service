package ru.raiffeisen.custody.example.spingdata.delegate.management;

import com.example.techcardservice.dto.CardDto;
import com.example.techcardservice.dto.CardRelationComponentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.ManagementUtils;
import ru.raiffeisen.custody.example.spingdata.aop.annotations.BusinessStep;
import ru.raiffeisen.custody.example.spingdata.api.WarehouseServiceApi;
import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class WarehouseDelegate implements JavaDelegate {
    private final ManagementUtils utils;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CardDto card = utils.getObject("card", CardDto.class, delegateExecution);
        BigDecimal qty = utils.getVariable("qty", BigDecimal.class, delegateExecution);
        boolean isRequired;

        List<CardRelationComponentDto> components = card.getComponents();

        ArrayList<CardRelationComponentDto> cards = components.stream()
                .filter(cardRelationComponentDto ->
                        cardRelationComponentDto.getComponent().getStock()
                                .compareTo(cardRelationComponentDto.getQty().multiply(qty)) < 0)
                .collect(Collectors.toCollection(ArrayList::new));

        cards.forEach(
                cardRelationComponentDto -> {
                    BigDecimal requiredStock = cardRelationComponentDto.getQty().multiply(qty)
                            .subtract(cardRelationComponentDto.getComponent().getStock());
                    cardRelationComponentDto.getComponent().setStock(requiredStock);
                }
        );
        List<ComponentDto> requiredCards = cards.stream().map(CardRelationComponentDto::getComponent).collect(Collectors.toList());
        log.info("cards {}", cards);

        isRequired = cards.isEmpty();

        utils.setObject("requiredCards", requiredCards, delegateExecution);
        delegateExecution.setVariable("isComponentPresent", isRequired);
    }
}
