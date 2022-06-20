package com.example.foodbackend.payment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "PaymentGeneratedLinkDTO Model", description = "PaymentGeneratedLinkDTO model which is response from /api/payment-with-order-id")
public class PaymentGeneratedLinkDTO {

    @ApiModelProperty(notes = "Generated payment link", position = 1)
    private String generatedLink;

    @ApiModelProperty(notes = "Order of payment", position = 1)
    private String paymentOrderId;
}
