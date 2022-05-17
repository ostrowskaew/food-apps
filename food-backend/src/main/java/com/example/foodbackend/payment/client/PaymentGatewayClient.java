package com.example.foodbackend.payment.client;

import com.example.foodbackend.payment.dto.PaymentDTO;
import com.example.foodbackend.payment.dto.PaymentGeneratedLinkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "payment-gateway", url = "${payment.gateway.url}")
public interface PaymentGatewayClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/payment", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PaymentGeneratedLinkDTO> payOrder(
            @RequestBody PaymentDTO finalRequestObject);
}