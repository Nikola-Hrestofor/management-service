package ru.raiffeisen.custody.example.spingdata.delegate.management;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.ManagementUtils;
import ru.raiffeisen.custody.example.spingdata.aop.annotations.BusinessStep;
import ru.raiffeisen.custody.example.spingdata.dto.FactoryDto;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class FactoryTaskDelegate implements JavaDelegate {

    @Autowired
    public JmsTemplate jmsTemplate;

    @Value("${factory.jms.queue-out}")
    String queue;

    private final ManagementUtils utils;
    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Long cardId = (Long) delegateExecution.getVariable("cardId");
        BigDecimal qty = (BigDecimal) delegateExecution.getVariable("qty");
        String processId = delegateExecution.getProcessInstanceId();

        log.info("cardId {}, qty {}", cardId, qty);
//        delegateExecution.setVariable("rest", qty);
        utils.setObject("require", qty, delegateExecution);
        FactoryDto factoryDto = new FactoryDto(cardId, qty);
        jmsTemplate.convertAndSend(queue, factoryDto, message -> {
            message.setStringProperty("processId", processId);
            return message;
        });
        log.info("Factory task was send with process id {}", processId
        );
    }
}
