package com.example.foodpg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class INGPaymentResponseDTO {
    private String url;
    private String orderId;
}
