package com.example.foodbackend.controller;
import com.example.foodbackend.model.Product;
import com.example.foodbackend.service.ProductService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/all-products")
    public List<Product> getAllProducts() {
        try {
            return productService.getAllProducts();
        } catch (Exception ex)
        {
            log.error("Exception getting all products", ex);
            return null;
        }
    }


    @GetMapping("/api/product-by-id")
    public Product getProduct(@ApiParam(value = "Id of product", required = true) @NotNull @RequestParam(value = "productId") Long productId ) {
        try {
            return productService.getProduct(productId);
        } catch (Exception ex)
        {
            log.error("Exception getting product by id", ex);
            return null;
        }
    }



    @GetMapping("/api/product-by-name")
    public List<Product> getProductByName(@ApiParam(value = "Searched term in product names", required = true)
                                              @NotNull @RequestParam(value = "searchedProductName") String searchedProductName) {
        try {
            return productService.getProductByName(searchedProductName);
        }
        catch (Exception ex)
        {
            log.error("Exception getting product by name", ex);
            return null;
        }
    }


}
