package com.example.foodbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "ProductDTO Model", description = "ProductDTO model")
public class ProductDTO {

    @ApiModelProperty(notes = "Id of the product ", required = true, position = 0)
    @JsonProperty(required = true)
    private long id;

    @ApiModelProperty(notes = "Name of the product ", required = true, position = 1)
    @JsonProperty(required = true)
    private String name;

    @ApiModelProperty(notes = "Price of the product ", required = true, position = 2)
    @JsonProperty(required = true)
    private long price;
}