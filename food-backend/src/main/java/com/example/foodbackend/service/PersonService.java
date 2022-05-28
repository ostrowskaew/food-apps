package com.example.foodbackend.service;

import com.example.foodbackend.dto.SocialUserDTO;
import com.example.foodbackend.model.Order;
import com.example.foodbackend.model.Person;
import com.example.foodbackend.model.Product;
import com.example.foodbackend.repository.PersonRepository;
import com.example.foodbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person create(SocialUserDTO socialUserDTO) {
        Person person = new Person(socialUserDTO);
        return this.personRepository.save(person);
    }

    public Person getPersonById(String id) {
        Optional<Person> optPerson = personRepository.findById(id);
        return optPerson.orElse(null);

    }
}
