package com.example.foodpg.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class INGTransactionRequestDTO {
    private String serviceId;
    private Long amount;
    private String currency;
    private String orderId;
    private String title;
    private String returnUrl;
    private String successReturnUrl;
    private String failureReturnUrl;
    private INGCustomerDTO customer;
}