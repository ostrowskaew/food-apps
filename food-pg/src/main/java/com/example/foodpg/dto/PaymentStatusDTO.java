package com.example.foodpg.dto;

import com.example.foodpg.enums.PaymentStatus;
import com.example.foodpg.enums.PaymentStatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@ApiModel(value = "PaymentStatusDTO Model", description = "PaymentStatusDTO model which is response from /api/status-inside")
public class PaymentStatusDTO {

    @ApiModelProperty(notes = "OrderId", required = true, position = 0)
    private BigInteger orderId;

    @ApiModelProperty(notes = "Status of payment", required = true, position = 1)
    private PaymentStatus paymentStatus;

    @ApiModelProperty(notes = "Payment amount", required = true, position = 2)
    private BigDecimal amount;

    @ApiModelProperty(notes = "Payment date", required = true, position = 3)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime paymentDate;

    @ApiModelProperty(notes = "Status type of payment", required = true, position = 5)
    private PaymentStatusType paymentStatusType;

    public PaymentStatusDTO(BigInteger orderId, PaymentStatus paymentStatus, BigDecimal amount, LocalDateTime paymentDate) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.paymentDate = paymentDate;
        if (paymentStatus != null) {
            this.paymentStatusType = paymentStatus.getPaymentStatusType();
        }
    }
}
