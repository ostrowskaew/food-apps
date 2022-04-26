package com.example.foodpg.entity;

import com.example.foodpg.enums.PaymentStatusEnum;
import com.example.foodpg.enums.PaymentStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private PaymentStatusEnum paymentStatus;
    private PaymentStatusType paymentStatusType;
}
