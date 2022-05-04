package com.example.foodpg.service;

import com.example.foodpg.dto.INGResponseDTO;
import com.example.foodpg.dto.INGSignatureDTO;
import com.example.foodpg.dto.INGTransactionResponseDTO;
import com.example.foodpg.entity.Payment;
import com.example.foodpg.enums.PaymentStatus;
import com.example.foodpg.repo.PaymentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.hash.Hashing;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class NotificationService {

    private final String serviceKey;
    private final ObjectMapper objectMapper;
    private final PaymentRepo paymentRepo;

    @Autowired
    public NotificationService(@Value("${ing.service.key}") final String serviceKey,
                               PaymentRepo paymentRepo) {
        this.serviceKey = serviceKey;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.paymentRepo = paymentRepo;
    }

    public ResponseEntity<String> processNotification(String signature, String ingPaymentStatusNotificationBody) throws Exception {
        if (!isSignatureValid(signature, ingPaymentStatusNotificationBody))
            throw new Exception("Hash is incorrect.");
        return processTransactionStatusRequest(objectMapper.readValue(ingPaymentStatusNotificationBody, INGResponseDTO.class).getINGtransactionResponseDTO());
    }

    public boolean isSignatureValid(String signature, String ingPaymentStatusNotificationBody) {
        INGSignatureDTO ingSignatureDTO = mapSignatureToSignatureDTO(signature);
        String currentSignature = generateHashSignatureForBody(ingPaymentStatusNotificationBody);
        return ingSignatureDTO.getSignature().equals(currentSignature);
    }

    public String generateHashSignatureForBody(final String body) {
        return Hashing.sha256()
                .hashString(body + serviceKey, StandardCharsets.UTF_8)
                .toString();
    }

    private INGSignatureDTO mapSignatureToSignatureDTO(String signature) {
        Map<String, String> signatureMap = Arrays.stream(signature.split(";"))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
        return objectMapper.convertValue(signatureMap, INGSignatureDTO.class);
    }

    public ResponseEntity<String> processTransactionStatusRequest(INGTransactionResponseDTO ingTransactionNotification) throws Exception {
        String status = ingTransactionNotification.getStatus();
        final Payment payment = getPayment(ingTransactionNotification.getOrderId());
        final PaymentStatus responsePaymentStatus = PaymentStatus.getPaymentStatusByStatusName(status);
        if (payment.getPaymentStatus() == null || (payment.getPaymentStatus() != PaymentStatus.SETTLED)) {
            setPaymentInfo(payment, responsePaymentStatus, ingTransactionNotification);
        }

        return new ResponseEntity<>("{\"status\":\"ok\"}", HttpStatus.OK);
    }

    private void setPaymentInfo(Payment payment, PaymentStatus paymentStatus, INGTransactionResponseDTO ingTransactionNotification) {
        payment.setPaymentUpdate(LocalDateTime.ofInstant(ingTransactionNotification.getModified(), ZoneOffset.UTC));
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentStatusType(paymentStatus.getPaymentStatusType());
        paymentRepo.save(payment);
    }

    private Payment getPayment(String orderId) throws Exception {
        final Optional<Payment> payment = paymentRepo.findById(Long.valueOf(orderId));
        if (!payment.isPresent()) {
            throw new Exception("Payment not found");
        }
        return payment.get();
    }
}
