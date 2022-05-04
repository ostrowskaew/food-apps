package com.example.foodpg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class INGSignatureDTO {

    @JsonProperty("merchantid")
    private String merchantId;

    @JsonProperty("signature")
    private String signature;

    @JsonProperty("serviceid")
    private String serviceId;

    @JsonProperty("alg")
    private String alg;
}
