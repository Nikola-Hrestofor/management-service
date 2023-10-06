package ru.raiffeisen.custody.example.spingdata.api;

import com.example.techcardservice.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tech-card", path = "/api/v1/cards", url = "${spring.services.tech-card.host}")
public interface TechCardServiceApi {
    @GetMapping(value = "/{id}")
    CardDto getCard(@PathVariable Long id);
}
