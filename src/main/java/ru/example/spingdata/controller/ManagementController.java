package ru.example.spingdata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;
import ru.example.spingdata.dto.ComponentDto;
import ru.example.spingdata.dto.ComponentProcess;
import ru.example.spingdata.service.ProcessService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("data")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ManagementController {

    private final RuntimeService runtimeService;
    private final ProcessService processService;

    @PostMapping
    public void start(@RequestParam Long cardId, BigDecimal qty) {
        log.info("Start process cardId {}; qty {}", cardId, qty);
        runtimeService.startProcessInstanceByKey("management", Map.of(
                "cardId", cardId,
                "qty", qty
        ));
    }

    @GetMapping("/processes")
    public List<ComponentProcess> getProcesses() {
        return processService.getProcesses();
    }

    @GetMapping("/processes/{id}")
    public List<ComponentDto> getProcess(@PathVariable String id) {
        return processService.getProcess(id);
    }

    @DeleteMapping("/processes/{id}")
    public void deleteProcess(@PathVariable String id) {
        processService.deleteProcess(id);
    }


    @PutMapping("/task/{id}")
    public void approveTask(@PathVariable String id) {
        processService.approveTask(id);
    }

    @PostMapping("/receive/{id}")
    public void receive(@PathVariable String id) {
        processService.receive(id);
    }
}
