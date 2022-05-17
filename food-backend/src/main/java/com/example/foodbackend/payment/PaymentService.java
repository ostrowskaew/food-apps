package com.example.foodbackend.payment;

import com.example.foodbackend.model.Order;
import com.example.foodbackend.payment.client.PaymentGatewayClient;
import com.example.foodbackend.payment.dto.PaymentDTO;
import com.example.foodbackend.payment.dto.PaymentGeneratedLinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentGatewayClient paymentGatewayClient;

    @Autowired
    public PaymentService(PaymentGatewayClient paymentGatewayClient) {
        this.paymentGatewayClient = paymentGatewayClient;
    }

    public PaymentGeneratedLinkDTO payOrder(Order order) throws Exception {
        ResponseEntity<PaymentGeneratedLinkDTO> responseEntity = paymentGatewayClient.payOrder(generatePatmentDtoFromOrder(order));

        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new Exception("PG error with status code : " + responseEntity.getStatusCodeValue());

        return responseEntity.getBody();
    }

    private PaymentDTO generatePatmentDtoFromOrder(Order order) {
        return new PaymentDTO(
                BigDecimal.valueOf(order.getTotalOrderPrice()),
                order.getId().toString(),
                "nightgrodno@gmail.com",
                "Abobik",
                "Abobow",
                "192.168.1.1"
        );
    }
}
