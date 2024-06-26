package ru.example.spingdata.delegate.management;

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

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductWarehouseDelegate implements JavaDelegate {
    private final ManagementUtils utils;
    private final WarehouseServiceApi warehouseServiceApi;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("ProductWarehouseDelegate");

        BigDecimal enrollStock = utils.getVariable("enrollStock", BigDecimal.class, delegateExecution);
        BigDecimal require = utils.getObject("require", BigDecimal.class, delegateExecution);
        Long id = utils.getVariable("cardId", Long.class, delegateExecution);

//        BigDecimal enrollStock = (BigDecimal) delegateExecution.getVariable("enrollStock");
//        BigDecimal require = (BigDecimal) delegateExecution.getVariable("require");

        log.info("enrollStock {}", enrollStock);
        log.info("require {}", require);

//        log.info("enrollStock1 {}", enrollStock1);
//        log.info("require1 {}", require1);

        require = require.subtract(enrollStock);

        utils.setObject("require", require, delegateExecution);

//        warehouseServiceApi.addProduction(id, enrollStock);

        warehouseServiceApi.addNewUnit(WarehouseRequest.builder()
                        .amount(enrollStock)
                        .childId(id)
                        .cost(BigDecimal.ZERO)
                        .type(UnitType.CARD)
                        .orderNumber("")
                .build());

        if (require.compareTo(BigDecimal.ZERO) > 0)
            delegateExecution.setVariable("isFactoryFinish", false);
        else delegateExecution.setVariable("isFactoryFinish", true);

    }
}
