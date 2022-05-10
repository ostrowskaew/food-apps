package com.example.foodbackend.dto;

import com.example.foodbackend.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(value = "OrderProductDTO Model", description = "OrderProductDTO model")
public class OrderProductDto {

    @ApiModelProperty(notes = "Name of the product in the order", required = true, position = 0)
    @JsonProperty(required = true)
    private Product product;

    @ApiModelProperty(notes = "Quantity of ordered products of a type", required = true, position = 1)
    @JsonProperty(required = true)
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}