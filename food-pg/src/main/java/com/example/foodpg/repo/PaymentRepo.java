package com.example.foodpg.repo;

import com.example.foodpg.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Long> {
}
