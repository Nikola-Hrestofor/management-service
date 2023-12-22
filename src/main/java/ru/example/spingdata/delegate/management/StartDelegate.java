package ru.example.spingdata.delegate.management;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.ManagementUtils;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDelegate implements JavaDelegate {
    private final ManagementUtils utils;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("id1 {}", delegateExecution.getVariable("cardId"));
        log.info("qty1 {}", delegateExecution.getVariable("qty"));

        BigDecimal qty = (BigDecimal) delegateExecution.getVariable("qty");
        utils.setObject("require", qty, delegateExecution);
    }
}
