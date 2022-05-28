package com.example.foodbackend.model;


import com.example.foodbackend.dto.ShippingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shippingDetails")
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "phone")
    private String phone;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "company")
    private String company;

    public ShippingDetails(ShippingDTO shippingDTO) {
        this.firstName = shippingDTO.getFirstName();
        this.lastName = shippingDTO.getLastName();
        this.email = shippingDTO.getEmail();
        this.phone = shippingDTO.getPhone();
        this.city = shippingDTO.getCity();
        this.postCode = shippingDTO.getPostalCode();
        this.building = shippingDTO.getBuilding();
        this.apartment = shippingDTO.getApartment();
        this.company = shippingDTO.getCompany();
    }

}
