package ru.example.spingdata.delegate.presale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.ManagementUtils;
import ru.example.spingdata.aop.annotations.BusinessStep;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class PresaleTaskDelegate implements JavaDelegate {

    private final ManagementUtils utils;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Long qty = (Long) delegateExecution.getVariable("count");
        String processId = delegateExecution.getProcessInstanceId();

        utils.setObject("require", BigDecimal.valueOf(qty), delegateExecution);

        log.info("Factory task was send with process id {}", processId
        );
    }
}
