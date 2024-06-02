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

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class PresaleWarehouseDelegate implements JavaDelegate {
    private final ManagementUtils utils;
    private final WarehouseServiceApi warehouseServiceApi;

    @Override
    @BusinessStep
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("PresaleWarehouseDelegate");

        BigDecimal enrollStock = utils.getVariable("enrollStock", BigDecimal.class, delegateExecution);
        BigDecimal require = utils.getObject("require", BigDecimal.class, delegateExecution);
        Long id = utils.getVariable("cardId", Long.class, delegateExecution);


        log.info("enrollStock {}", enrollStock);
        log.info("require {}", require);

        require = require.subtract(enrollStock);

        utils.setObject("require", require, delegateExecution);


        warehouseServiceApi.addNewUnit(WarehouseRequest.builder()
                        .amount(enrollStock)
                        .childId(id)
                        .cost(BigDecimal.ZERO)
                        .type(UnitType.PRODUCT)
                        .orderNumber("")
                .build());

        if (require.compareTo(BigDecimal.ZERO) > 0)
            delegateExecution.setVariable("isFactoryFinish", false);
        else delegateExecution.setVariable("isFactoryFinish", true);

    }
}
