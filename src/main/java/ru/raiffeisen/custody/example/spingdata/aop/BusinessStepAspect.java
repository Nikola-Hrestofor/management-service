package ru.raiffeisen.custody.example.spingdata.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BusinessStepAspect {
    @Before("@annotation(ru.raiffeisen.custody.example.spingdata.aop.annotations.BusinessStep) " +
            "&& args(org.camunda.bpm.engine.delegate.DelegateExecution) && args(delegateExecution)")
    public void beforeStep(DelegateExecution delegateExecution){
        step(delegateExecution);
    }

    private void step(DelegateExecution delegateExecution){
        delegateExecution.setVariable("stepName", delegateExecution.getCurrentActivityName());
    }
}