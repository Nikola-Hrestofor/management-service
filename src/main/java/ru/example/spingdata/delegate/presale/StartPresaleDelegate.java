package ru.example.spingdata.delegate.presale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.ManagementUtils;
import ru.example.spingdata.aop.annotations.BusinessStep;
import ru.example.spingdata.entity.ProductEntity;
import ru.example.spingdata.repository.ProductRepository;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartPresaleDelegate implements JavaDelegate {
    private final ProductRepository productRepository;
    private final ManagementUtils utils;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Presale start delegate");

        UUID uuid = utils.getVariable("uuid", UUID.class, delegateExecution);
        String name = utils.getVariable("name", String.class, delegateExecution);

        log.info("uuid {}", uuid);
        log.info("count {}", delegateExecution.getVariable("count"));
        log.info("name {}", name);

        Long cardId = productRepository.save(new ProductEntity(null, uuid, name)).getId();

        delegateExecution.setVariable("cardId", cardId);

    }
}
