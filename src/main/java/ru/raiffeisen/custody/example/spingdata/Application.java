package ru.raiffeisen.custody.example.spingdata;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Application
 *
 * @author Rail Iulgutlin Raiffeisen Bank Russia 12.10.2021
 */
@EnableFeignClients
@SpringBootApplication
@EnableProcessApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
