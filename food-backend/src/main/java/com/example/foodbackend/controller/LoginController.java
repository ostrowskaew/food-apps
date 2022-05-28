package com.example.foodbackend.controller;

import com.example.foodbackend.dto.JwtResponseDTO;
import com.example.foodbackend.dto.SocialUserDTO;
import com.example.foodbackend.service.LoginService;
import com.example.foodbackend.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PersonService personService;

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponseDTO> loginUser(@RequestBody SocialUserDTO socialUser) {
        try {
            ResponseEntity<JwtResponseDTO> res = loginService.loginUser(socialUser);
            personService.create(socialUser);
            return res;
        } catch (Exception ex) {
            log.error("Exception loging user", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
