package com.example.foodpg.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public ResponseEntity<String> processNotification(String signature, String ingPaymentStatusNotificationBody) throws Exception {
        return new ResponseEntity<>("{\"status\":\"ok\"}", HttpStatus.OK);
    }
}
