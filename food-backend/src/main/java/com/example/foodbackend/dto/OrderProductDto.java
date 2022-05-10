package com.example.foodbackend.dto;

import com.example.foodbackend.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderProductDto {

    private Product product;
    private Integer quantity;
}