package com.example.foodbackend.controller;

import com.example.foodbackend.dto.JwtResponseDTO;
import com.example.foodbackend.dto.SocialUserDTO;
import com.example.foodbackend.service.LoginService;
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

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponseDTO> loginUser(@RequestBody SocialUserDTO socialUser) {
        try {
            return loginService.loginUser(socialUser);
        } catch (Exception ex) {
            log.error("Exception getting all products", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
