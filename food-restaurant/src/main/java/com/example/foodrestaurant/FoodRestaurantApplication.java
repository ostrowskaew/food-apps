package com.example.foodrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FoodRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodRestaurantApplication.class, args);
    }

}
