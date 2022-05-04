package com.example.foodpg.entity;

import com.example.foodpg.enums.PaymentStatus;
import com.example.foodpg.enums.PaymentStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue
    private Long orderId;
    private String orderNumber;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime paymentDate;
    private LocalDateTime paymentUpdate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatusType;
}
