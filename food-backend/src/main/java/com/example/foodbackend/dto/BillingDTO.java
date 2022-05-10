package com.example.foodbackend.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillingDTO {
    private String firstName;
    private String lastName;
    private String company;
    private String street;
    private String city;
    private String region;
    private String postalCode;
    private String countryCodeAlpha2;

}
