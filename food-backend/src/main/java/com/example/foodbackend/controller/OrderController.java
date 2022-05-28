package com.example.foodbackend.controller;

import com.example.foodbackend.dto.OrderProductDto;
import com.example.foodbackend.dto.ShippingDTO;
import com.example.foodbackend.model.Order;
import com.example.foodbackend.model.OrderProduct;
import com.example.foodbackend.model.OrderStatus;
import com.example.foodbackend.model.ShippingDetails;
import com.example.foodbackend.payment.dto.PaymentGeneratedLinkDTO;
import com.example.foodbackend.service.*;
import com.example.foodbackend.payment.PaymentService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private PersonService personService;


    @GetMapping("/api/orders")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull
    Iterable<Order> list() {
        try {
            return orderService.getAllOrders();
        } catch (Exception ex) {
            log.error("Exception getting all orders", ex);
            return null;
        }

    }

    @GetMapping("/api/orders-by-user-id")
    public Order getUsersOrder(@ApiParam(value = "Id of user", required = true) @NotNull @RequestParam(value = "userId") Long userId) {
        try {
            return orderService.getUsersOrders(userId);
        } catch (Exception ex) {
            log.error("Exception getting product by id", ex);
            return null;
        }
    }


    @PostMapping("/api/orders")
    public ResponseEntity<PaymentGeneratedLinkDTO> create(@RequestBody Wrapper wrapper) {

        ShippingDetails shippingDetails = new ShippingDetails(wrapper.shippingDetails);
        shippingDetails = shippingService.create(shippingDetails);

        List<OrderProductDto> formDtos = wrapper.order.getProductOrders();
        Order order = new Order();
        order.setStatus(OrderStatus.UNPAID.name());
        order.setShippingDetails(shippingDetails);
        order.setPerson(personService.getPersonById(wrapper.userId));
        order = orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        orderService.update(order);

        try {
            PaymentGeneratedLinkDTO paymentGeneratedLinkDTO = paymentService.payOrder(order);

            String uri = ServletUriComponentsBuilder
                    .fromCurrentServletMapping()
                    .path("/orders/{id}")
                    .buildAndExpand(order.getId())
                    .toString();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", uri);
            order.setStatus(OrderStatus.PAID.name());
            orderService.update(order);

            return new ResponseEntity<>(paymentGeneratedLinkDTO, headers, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }


    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }
    }

    @Data
    public static class Wrapper {
        OrderForm order;
        ShippingDTO shippingDetails;
        String userId;

    }



}
