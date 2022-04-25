package com.example.foodpg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ApiModel(value = "PaymentDTO Model", description = "PaymentDTO model which is required to generate new payment link")
public class PaymentDTO {

    @ApiModelProperty(notes = "Sum of payments in format 0.00 ", required = true, position = 0)
    @JsonProperty(required = true)
    private BigDecimal sumOfAmount;

    @ApiModelProperty(notes = "Order number", position = 1)
    @JsonProperty(required = true)
    private String caseNumber;

    @ApiModelProperty(notes = "Customer e-mail address", position = 12)
    @JsonProperty(value = "customerEmail", required = false)
    private String customerEmail;

    @ApiModelProperty(notes = "Customer first name", position = 13)
    @JsonProperty(value = "customerFirstName", required = false)
    private String customerFirstName;

    @ApiModelProperty(notes = "Customer last name", position = 14)
    @JsonProperty(value = "customerLastName", required = false)
    private String customerLastName;

    @ApiModelProperty(notes = "Customer IP address", position = 15)
    @JsonProperty(value = "customerIp", required = false)
    private String customerIp;
}
