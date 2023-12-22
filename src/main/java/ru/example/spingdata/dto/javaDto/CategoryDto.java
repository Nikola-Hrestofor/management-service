package ru.example.spingdata.dto.javaDto;

import lombok.Data;

@Data
public class CategoryDto {
    Long id;
    String name;
    int qty;
}
