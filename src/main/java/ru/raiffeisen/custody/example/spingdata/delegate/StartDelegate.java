package ru.raiffeisen.custody.example.spingdata.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("id1 {}", delegateExecution.getVariable("cardId"));
        log.info("qty1 {}", delegateExecution.getVariable("qty"));
    }
}
