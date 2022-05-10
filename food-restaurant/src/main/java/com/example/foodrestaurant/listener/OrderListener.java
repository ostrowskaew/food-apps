package com.example.foodrestaurant.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Log4j2
public class OrderListener {

    @RabbitListener(queues = "order.queue")
    public void processOrderQueueEvent(String message) {
        log.warn("Process Order Queue Event: " + message);
    }
}
