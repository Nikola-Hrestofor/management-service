package ru.example.spingdata.delegate.presale;

import ru.example.spingdata.api.dto.enums.UnitType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.example.spingdata.ManagementUtils;
import ru.example.spingdata.aop.annotations.BusinessStep;
import ru.example.spingdata.api.WarehouseServiceApi;
import ru.example.spingdata.api.dto.WarehouseRequest;
import ru.example.spingdata.entity.PresaleEntity;
import ru.example.spingdata.repository.PresaleRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class WriteOffPresaleDelegate implements JavaDelegate {
    private final WarehouseServiceApi warehouseService;
    private final ManagementUtils utils;
    private final PresaleRepository presaleRepository;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        UUID uuid = utils.getVariable("uuid", UUID.class, delegateExecution);

        List<PresaleEntity> entities = presaleRepository.getAllByUuid(uuid);
        Long count = utils.getVariable("count", Long.class, delegateExecution);


        log.info("write off presale note {}", entities);

        BigDecimal costs = BigDecimal.ZERO;

        entities.forEach(presale -> {
            BigDecimal cost = warehouseService.seizeUnit(WarehouseRequest.builder()
                    .amount(presale.getQty().multiply(BigDecimal.valueOf(count)))
                    .cost(BigDecimal.ZERO)
                    .childId(presale.getItemId())
                    .orderNumber("")
                    .type(UnitType.valueOf(presale.getType()))
                    .build());
            costs.add(cost);
            //TODO costs = costs.add(cost);
        });

        delegateExecution.setVariable("componentsCost", costs);
        log.info("cost {}", costs);
    }
}
