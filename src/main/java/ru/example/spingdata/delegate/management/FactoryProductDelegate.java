package ru.example.spingdata.delegate.management;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.aop.annotations.BusinessStep;

@Slf4j
@Component
public class FactoryProductDelegate implements JavaDelegate {
    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Factory task was receive ");
    }
}
