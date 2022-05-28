package com.example.foodbackend.service;

import com.example.foodbackend.model.Order;
import com.example.foodbackend.model.ShippingDetails;
import com.example.foodbackend.repository.ProductRepository;
import com.example.foodbackend.repository.ShippingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    @Autowired
    private ShippingDetailsRepository shippingDetailsRepository;

    public ShippingDetails create(ShippingDetails shippingDetails) {

        return this.shippingDetailsRepository.save(shippingDetails);
    }
}
