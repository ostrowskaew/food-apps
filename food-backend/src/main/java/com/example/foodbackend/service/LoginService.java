package com.example.foodbackend.service;

import com.example.foodbackend.dto.JwtResponseDTO;
import com.example.foodbackend.dto.SocialUserDTO;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret;

    public LoginService(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public ResponseEntity<JwtResponseDTO> loginUser(SocialUserDTO socialUser) {
        final String token = generateToken(socialUser);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    public String generateToken(SocialUserDTO socialUser) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, socialUser.getEmail());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
