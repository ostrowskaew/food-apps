package com.example.foodbackend.service;

import com.example.foodbackend.model.OrderProduct;
import com.example.foodbackend.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}