package com.example.foodbackend.service;

import com.example.foodbackend.model.Order;
import com.example.foodbackend.model.Product;
import com.example.foodbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class OrderService{

    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());

        return this.orderRepository.save(order);
    }

    public Order getUsersOrders(Long id) {
        Optional<Order> optOrder = orderRepository.findById(id);
        return optOrder.orElse(null);
    }

    public void update(Order order) {
        this.orderRepository.save(order);
    }
}