package com.example.foodpg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class INGTransactionResponseDTO {

    private String status;
    private String orderId;
    private Instant modified;
}
