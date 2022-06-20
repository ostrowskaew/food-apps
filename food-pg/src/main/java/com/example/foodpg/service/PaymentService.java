package com.example.foodpg.service;

import com.example.foodpg.client.PaymentClient;
import com.example.foodpg.dto.*;
import com.example.foodpg.entity.Payment;
import com.example.foodpg.enums.PaymentStatusType;
import com.example.foodpg.exception.PaymentLinkGenerationException;
import com.example.foodpg.repo.PaymentRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
public class PaymentService {

    private final static String BEARER = "Bearer ";

    private final PaymentClient paymentClient;
    private final PaymentRepo paymentRepo;

    private final String apiKey;
    private final String serviceId;
    private final String returnUrl;

    @Autowired
    public PaymentService(@Value("${ing.api.key}") final String apiKey,
                          @Value("${ing.service.id}") final String serviceId,
                          @Value("${ing.url.return}") final String returnUrl,
                          PaymentClient paymentClient,
                          PaymentRepo paymentRepo) {
        this.apiKey = apiKey;
        this.serviceId = serviceId;
        this.returnUrl = returnUrl;
        this.paymentClient = paymentClient;
        this.paymentRepo = paymentRepo;
    }

    public String createBearerAccessToken() {
        return BEARER + apiKey;
    }


    public PaymentGeneratedLinkDTO startPayment(PaymentDTO paymentDTO) throws PaymentLinkGenerationException {
        Payment payment = generateNewPaymentEntity(paymentDTO);
        INGPaymentResponseDTO INGPaymentResponseDTO = generatePaymentLink(payment, paymentDTO);
        return PaymentGeneratedLinkDTO
                .builder()
                .paymentOrderId(INGPaymentResponseDTO.getOrderId())
                .generatedLink(INGPaymentResponseDTO.getUrl())
                .build();
    }

    private Payment generateNewPaymentEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setOrderNumber(paymentDTO.getOrderNumber());
        payment.setAmount(paymentDTO.getSumOfAmount());
        payment.setCurrency("PLN");
        payment.setPaymentDate(LocalDateTime.now());
        payment = paymentRepo.save(payment);
        return payment;
    }

    public INGPaymentResponseDTO generatePaymentLink(Payment payment, PaymentDTO paymentDTO) throws PaymentLinkGenerationException {
        ResponseEntity<INGResponseDTO> responseEntity = paymentClient.generatePaymentLink(createBearerAccessToken(), getTransactionRequestDTO(payment, paymentDTO));
        try {
            return responseEntity.getBody().getINGPaymentResponseDTO();
        } catch (Exception ex) {
            log.error(responseEntity.getBody());
            throw new PaymentLinkGenerationException("Problem during generation of ING multi payout link");
        }
    }

    private INGTransactionRequestDTO getTransactionRequestDTO(Payment payment, PaymentDTO paymentDTO) {
        String url = returnUrl + "?OrderID=" + payment.getOrderId();
        INGCustomerDTO INGCustomerDTO = generateCustomerDTO(paymentDTO);
        INGTransactionRequestDTO transactionRequestDTO = new INGTransactionRequestDTO();
        transactionRequestDTO.setServiceId(serviceId);
        transactionRequestDTO.setAmount(paymentDTO.getSumOfAmount().movePointRight(2).longValue());
        transactionRequestDTO.setCurrency(payment.getCurrency());
        transactionRequestDTO.setOrderId(String.valueOf(payment.getOrderId()));
        transactionRequestDTO.setTitle(payment.getOrderNumber());
        transactionRequestDTO.setReturnUrl(url);
        transactionRequestDTO.setFailureReturnUrl(url);
        transactionRequestDTO.setSuccessReturnUrl(url);
        transactionRequestDTO.setCustomer(INGCustomerDTO);
        return transactionRequestDTO;
    }

    public INGCustomerDTO generateCustomerDTO(PaymentDTO paymentDTO) {
        return INGCustomerDTO
                .builder()
                .firstName(paymentDTO.getCustomerFirstName())
                .lastName(paymentDTO.getCustomerLastName())
                .email(paymentDTO.getCustomerEmail())
                .build();
    }

    public PaymentStatusType getPaymentStatus(Long orderId) throws Exception {
        Payment payment = paymentRepo.findById(orderId).orElseThrow(Exception::new);
        if (payment.getPaymentStatusType() == null)
            throw new Exception();
        return payment.getPaymentStatusType();
    }
}
