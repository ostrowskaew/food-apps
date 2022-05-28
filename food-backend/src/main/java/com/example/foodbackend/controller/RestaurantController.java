package com.example.foodbackend.controller;

import com.example.foodbackend.dto.JwtResponseDTO;
import com.example.foodbackend.dto.SocialUserDTO;
import com.example.foodbackend.model.Product;
import com.example.foodbackend.model.Restaurant;
import com.example.foodbackend.service.LoginService;
import com.example.foodbackend.service.PersonService;
import com.example.foodbackend.service.ProductService;
import com.example.foodbackend.service.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/api/restaurants")
    public List<Restaurant> getAllRestaurants() {
        try {
            return restaurantService.getAllRestaurants();
        } catch (Exception ex)
        {
            log.error("Exception getting all restaurants", ex);
            return null;
        }
    }
}
