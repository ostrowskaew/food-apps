package com.example.foodbackend.controller;

import com.example.foodbackend.dto.OrderProductDto;
import com.example.foodbackend.model.Order;
import com.example.foodbackend.model.OrderProduct;
import com.example.foodbackend.model.OrderStatus;
import com.example.foodbackend.model.Product;
import com.example.foodbackend.service.OrderProductService;
import com.example.foodbackend.service.OrderService;
import com.example.foodbackend.service.ProductService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;


    @ApiOperation(value = "Get all orders",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @GetMapping("/api/orders")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull
    Iterable<Order> list() {
        try {
            return orderService.getAllOrders();
        } catch (Exception ex)
        {
            log.error("Exception getting all orders", ex);
            return null;
        }

    }

    @ApiOperation(value = "Get user's orders by id number",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @GetMapping("/api/orders-by-user-id")
    public Order getUsersOrder(@ApiParam(value = "Id of user", required = true) @NotNull @RequestParam(value = "userId") Long userId ) {
        try {
            return orderService.getUsersOrders(userId);
        } catch (Exception ex)
        {
            log.error("Exception getting product by id", ex);
            return null;
        }
    }


    @ApiOperation(value = "Create order",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error - not supported exception")
    })
    @PostMapping("/api/orders")
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }


    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
}
