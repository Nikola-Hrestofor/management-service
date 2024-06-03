package ru.example.spingdata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.example.spingdata.dto.ComponentDto;
import ru.example.spingdata.dto.ProductDto;
import ru.example.spingdata.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
