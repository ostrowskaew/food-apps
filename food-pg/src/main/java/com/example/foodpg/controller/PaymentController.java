package com.example.foodpg.controller;


import com.example.foodpg.dto.PaymentDTO;
import com.example.foodpg.dto.PaymentGeneratedLinkDTO;
import com.example.foodpg.enums.PaymentStatusType;
import com.example.foodpg.service.NotificationService;
import com.example.foodpg.service.PaymentService;
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

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    private final NotificationService notificationService;
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(NotificationService notificationService,
                             PaymentService paymentService) {
        this.notificationService = notificationService;
        this.paymentService = paymentService;
    }

    @ApiOperation(value = "Generate redirect service link with order id",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @PostMapping("/api/payment")
    public ResponseEntity<PaymentGeneratedLinkDTO> generateRedirectUrlDTOBasedOnPaymentDTO(
            @RequestBody @ApiParam(value = "PaymentDTO object", required = true) PaymentDTO payment) {
        try {
            return new ResponseEntity<PaymentGeneratedLinkDTO>(paymentService.startPayment(payment), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Unsupported Exception in method generateRedirectUrlDTOBasedOnPaymentDTO", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update service status in database. Method for webhook for ING.",
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
            return notificationService.processNotification(signature, ingPaymentStatusNotificationBody);
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
    @GetMapping("/api/status")
    public ResponseEntity<PaymentStatusType> checkPaymentStatus(@ApiParam(value = "Order id", required = true) @RequestParam Long orderId) {
        try {
            return new ResponseEntity<PaymentStatusType>(paymentService.getPaymentStatus(orderId), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception during processing notification in ING", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
