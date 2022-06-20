package com.example.foodbackend.service;

import com.example.foodbackend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;

@RestController
public class FinalizeOrderService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public FinalizeOrderService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }


    @Data
    @AllArgsConstructor
    public static class FinalizeOrder implements Serializable {
        Long orderId;
    }

    public void addOrderToQueue(Order order) {
        amqpTemplate.convertAndSend("order.queue", new FinalizeOrder(order.getId()));
    }
}
