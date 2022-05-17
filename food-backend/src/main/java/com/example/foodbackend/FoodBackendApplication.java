package com.example.foodbackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Log4j2
@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
public class FoodBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodBackendApplication.class, args);
    }

}
