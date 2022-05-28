package com.example.foodbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShippingDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String street;
    private String building;
    private String apartment;
    private String phone;
    private String city;
    private String postalCode;
}
