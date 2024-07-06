package ru.example.spingdata.controller

import org.springframework.web.bind.annotation.*
import ru.example.spingdata.service.BalanceService
import java.math.BigDecimal

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/balance")
class BalanceController(
    private val balanceService: BalanceService
) {
    @GetMapping("/{customerId}")
    fun balanceByCustomer(@PathVariable customerId: Long): BigDecimal =
        balanceService.getBalanceByCustomerId(customerId)
}