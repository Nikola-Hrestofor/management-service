package ru.raiffeisen.custody.example.spingdata.delegate.factory;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.aop.annotations.BusinessStep;

@Slf4j
@Component
public class FactoryTestDelegate implements JavaDelegate {
    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Factory was started ");
    }
}
