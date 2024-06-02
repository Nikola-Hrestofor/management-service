package ru.example.spingdata.delegate.presale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.aop.annotations.BusinessStep;

@Slf4j
@Component
@RequiredArgsConstructor
public class PresaleDelegate implements JavaDelegate {
    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Presale delegate");
    }
}
