package com.example.foodbackend.repository;

import com.example.foodbackend.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Restaurant findByRestaurantContainingIgnoreCase(String restaurant);
}
