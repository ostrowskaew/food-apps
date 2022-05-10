package com.example.foodpg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class INGCustomerDTO {
       private String firstName;
       private String lastName;
       private String email;
}
