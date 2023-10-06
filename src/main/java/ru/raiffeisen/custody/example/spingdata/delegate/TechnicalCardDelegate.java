package ru.raiffeisen.custody.example.spingdata.delegate;

import com.example.techcardservice.dto.CardDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.ManagementUtils;
import ru.raiffeisen.custody.example.spingdata.api.TechCardServiceApi;

import java.util.Optional;

/**
 * ExampleDelegate
 *
 * @author Rail Iulgutlin Raiffeisen Bank Russia 13.10.2021
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TechnicalCardDelegate implements JavaDelegate {

    private final TechCardServiceApi techCardServiceApi;
    private final ManagementUtils utils;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("id {}", execution.getVariable("cardId"));
        log.info("qty {}", execution.getVariable("qty"));
        Long cardId = utils.getVariable("cardId", Long.class, execution);
        CardDto card = getCard(cardId);
        utils.setObject("card", card, execution);
        execution.setVariable("cardName", card.getName());
        execution.setVariable("cardCode", card.getCode());
    }

    private CardDto getCard(Long cardId){
        CardDto card = techCardServiceApi.getCard(cardId);
        log.info("card {}", card);
        return card;
    }



}
