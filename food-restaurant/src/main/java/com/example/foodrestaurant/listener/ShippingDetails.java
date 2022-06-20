package com.example.foodrestaurant.listener;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetails {


    private Long id;


    private String firstName;


    private String lastName;


    private String email;


    private String street;


    private String building;


    private String apartment;


    private String phone;


    private String postCode;


    private String city;


    private String company;


}
