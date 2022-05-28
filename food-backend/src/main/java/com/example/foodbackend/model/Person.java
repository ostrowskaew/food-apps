package com.example.foodbackend.model;

import com.example.foodbackend.dto.SocialUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    public Person(SocialUserDTO socialUserDTO) {
        this.id = socialUserDTO.getId();
        this.name = socialUserDTO.getFirstName();
        this.surname = socialUserDTO.getLastName();
        this.email = socialUserDTO.getEmail();
    }
}
