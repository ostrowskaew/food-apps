package com.example.foodbackend.service;

import com.example.foodbackend.dto.OrderProductDto;
import com.example.foodbackend.model.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

//@Service
@RestController
public class FinalizeOrderService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public FinalizeOrderService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/send-order-to-queue")
    public void addOrderToQueue() {
        amqpTemplate.convertAndSend("order.queue", "ORDER:" + LocalDateTime.now());
    }
}
