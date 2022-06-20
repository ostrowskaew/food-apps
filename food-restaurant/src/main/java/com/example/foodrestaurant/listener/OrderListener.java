package com.example.foodrestaurant.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@EnableRabbit
@Component
@Log4j2
public class OrderListener {

    @Data
    @AllArgsConstructor
    public static class FinalizeOrder implements Serializable {
        Long orderId;
    }

    @RabbitListener(queues = "order.queue")
    public void processOrderQueueEvent(final Message message) {
        log.info("Received message as a generic AMQP 'Message' wrapper: {}", message.toString());
    }
}
