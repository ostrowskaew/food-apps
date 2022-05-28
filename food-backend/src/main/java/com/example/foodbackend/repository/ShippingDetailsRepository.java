package com.example.foodbackend.repository;

import com.example.foodbackend.model.ShippingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDetailsRepository extends CrudRepository<ShippingDetails, Long> {

}
