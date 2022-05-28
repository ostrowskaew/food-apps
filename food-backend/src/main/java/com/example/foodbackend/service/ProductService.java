package com.example.foodbackend.service;

import com.example.foodbackend.model.Product;
import com.example.foodbackend.model.Restaurant;
import com.example.foodbackend.repository.ProductRepository;
import com.example.foodbackend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.minidev.asm.DefaultConverter.convertToLong;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product getProduct(Long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        return optProduct.orElse(null);
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductByRestaurantId(Long restaurantId) {
        return productRepository.findByRestaurantId(restaurantId);
    }

    public List<Product> getProductByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByRestaurantContainingIgnoreCase(restaurantName);
        return productRepository.findByRestaurantId(restaurant.getId());
    }

}