package com.example.foodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FoodBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodBackendApplication.class, args);
    }

}
