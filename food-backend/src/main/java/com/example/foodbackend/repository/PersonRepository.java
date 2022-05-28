package com.example.foodbackend.repository;

import com.example.foodbackend.model.Person;
import com.example.foodbackend.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

}
