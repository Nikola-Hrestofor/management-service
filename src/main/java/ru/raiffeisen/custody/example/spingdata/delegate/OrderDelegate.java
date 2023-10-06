package ru.raiffeisen.custody.example.spingdata.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.aop.annotations.BusinessStep;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDelegate implements JavaDelegate {
    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("SMT WRONG. TBD");
    }
}
