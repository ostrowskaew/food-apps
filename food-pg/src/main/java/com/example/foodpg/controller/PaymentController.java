package com.example.foodpg.controller;


import com.example.foodpg.dto.PaymentDTO;
import com.example.foodpg.dto.PaymentGeneratedLinkDTO;
import com.example.foodpg.dto.PaymentStatusDTO;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@Log4j2
public class PaymentController {


    @Autowired
    public PaymentController() {

    }

    @ApiOperation(value = "Generate redirect service link with order id",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @PostMapping("/api/payment-with-order-id")
    public ResponseEntity<PaymentGeneratedLinkDTO> generateRedirectUrlDTOBasedOnPaymentDTO(
            @RequestBody @ApiParam(value = "PaymentDTO object", required = true) PaymentDTO payment) {
        try {
            return new ResponseEntity<>(new PaymentGeneratedLinkDTO(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Unsupported Exception in method generateRedirectUrlDTOBasedOnPaymentDTO", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update service status in database. Method only for ING.",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @PostMapping("/api/status")
    public ResponseEntity<String> processPaymentStatusNotification(@ApiParam(value = "Signature of webhook", required = true) @NotNull @RequestHeader(value = "X-IMoje-Signature") String signature,
                                                                   @ApiParam(value = "Body of ING webhook", required = true) @RequestBody String ingPaymentStatusNotificationBody) {
        try {
            return new ResponseEntity<>("{\"status\":\"ok\"}", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception during processing notification in ING", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Check payment status based on orderId.",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @GetMapping("/api/status-inside")
    public ResponseEntity<PaymentStatusDTO> checkPaymentStatus(@ApiParam(value = "Order id", required = true) @RequestParam BigInteger orderId) {
        ResponseEntity<PaymentStatusDTO> result;
        try {
            result = new ResponseEntity<>(new PaymentStatusDTO(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception during processing notification in ING", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
