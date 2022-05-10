package com.example.foodbackend.repository;

import com.example.foodbackend.model.OrderProduct;
import com.example.foodbackend.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}