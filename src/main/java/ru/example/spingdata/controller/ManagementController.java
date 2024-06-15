package ru.example.spingdata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.spingdata.dto.ComponentDto;
import ru.example.spingdata.dto.ComponentProcess;
import ru.example.spingdata.dto.FactoryDto;
import ru.example.spingdata.dto.PresaleRequest;
import ru.example.spingdata.service.PresaleService;
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
    private final PresaleService presaleService;

    @PostMapping
    public String start(@RequestParam Long cardId, BigDecimal qty) {
        log.info("Start process cardId {}; qty {}", cardId, qty);
        runtimeService.startProcessInstanceByKey("management", Map.of(
                "cardId", cardId,
                "qty", qty
        ));
        return "";
    }

    @PostMapping("/start/presale")
    public String startPresale(@RequestBody PresaleRequest items) {
        log.info("Start process presale {}", items);
        presaleService.startPresale(items);
        return "";
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

    @PostMapping("/receive/order/{id}")
    public void receiveOrder(@PathVariable String id) {
        processService.receiveOrder(id);
    }

    @PostMapping("/receive/factory/{id}")
    public void receiveFactory(@PathVariable String id, @RequestParam BigDecimal stock) {
        processService.receiveFactory(id, stock);
    }
}
