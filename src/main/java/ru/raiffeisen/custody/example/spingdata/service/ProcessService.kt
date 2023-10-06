package ru.raiffeisen.custody.example.spingdata.service

import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.runtime.Execution
import org.camunda.bpm.engine.task.Task
import org.springframework.stereotype.Service
import ru.raiffeisen.custody.example.spingdata.ManagementUtils
import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto
import ru.raiffeisen.custody.example.spingdata.dto.ComponentProcess
import java.util.*
import java.util.function.Consumer
import java.util.logging.Logger

@Service
class ProcessService(
    private val runtimeService: RuntimeService,
    private val managementUtils: ManagementUtils,
    val taskService: TaskService
) {
    companion object {
        val logger = Logger.getLogger(ProcessService::class.java.name)
    }

    fun getProcesses(): List<ComponentProcess> {

        val listProcesses = runtimeService.createExecutionQuery().active().list().stream().map(Execution::getId)
        val processes = mutableListOf<ComponentProcess>()
        listProcesses.forEach(Consumer { o: Any? ->
            run {
                logger.info("processId $o")
                val cardName = runtimeService.getVariables(o.toString())["cardName"].toString()
                val cardCode = runtimeService.getVariables(o.toString())["cardCode"].toString()
                val stepName = runtimeService.getVariables(o.toString())["stepName"].toString()
                processes.add(ComponentProcess(UUID.fromString(o.toString()), cardName, cardCode, stepName))
            }
        })

        return processes
    }

    fun getProcess(id: String): List<ComponentDto>? {
        val json = runtimeService.getVariables(id)["requiredCards"].toString()
        return managementUtils.getListComponents(json)
    }

    fun deleteProcess(id: String) {
        runtimeService.suspendProcessInstanceById(id)
    }

    fun approveTask(processId: String){
        val id = taskService.createTaskQuery().active().list()
            .stream()
            .filter { task: Task -> task.processInstanceId == processId }
            .map { obj: Task -> obj.id }
            .findFirst().orElse(null)

        taskService.complete(id)
    }
}