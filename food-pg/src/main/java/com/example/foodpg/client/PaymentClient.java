package com.example.foodpg.client;

import com.example.foodpg.dto.INGResponseDTO;
import com.example.foodpg.dto.INGTransactionRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ing", url = "${ing.basic.payment.url}" + "${ing.merchant.id}")
public interface PaymentClient {

    @RequestMapping(method = RequestMethod.POST, value = "/payment", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<INGResponseDTO> generatePaymentLink(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody INGTransactionRequestDTO finalRequestObject);
}