package ru.raiffeisen.custody.example.spingdata.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WrongDelegate implements JavaDelegate {
    private final RuntimeService runtimeService;
    TaskService taskService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("SMT WRONG");
//        log.info("list process {}", runtimeService.createExecutionQuery().active().list());
//        runtimeService.createExecutionQuery().active().list().stream().map(Execution::getId);
//        runtimeService.getVariables().get("key");

        delegateExecution.setVariable("stepName", delegateExecution.getCurrentActivityName());

//        String id = taskService.createTaskQuery().active().list()
//                .stream()
////                .map(Task::getProcessInstanceId)
////                .filter(s -> s.equals("id"))
//                .filter(task -> task.getProcessInstanceId().equals("rwr"))
//                .map(Task::getId)
//                .findFirst().orElse(null);

    }
}
