package ru.raiffeisen.custody.example.spingdata.dto.javaDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComponentDto {
    private final Long id;
    private final String name;
    private final String unit;
    private final CategoryDto category;
    private final String code;
    private final BigDecimal stock;
}
