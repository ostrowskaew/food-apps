package com.example.foodpg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class INGResponseDTO {
    @JsonProperty(value = "transaction")
    private INGTransactionResponseDTO INGtransactionResponseDTO;

    @JsonProperty(value = "payment")
    private INGPaymentResponseDTO INGPaymentResponseDTO;
}
