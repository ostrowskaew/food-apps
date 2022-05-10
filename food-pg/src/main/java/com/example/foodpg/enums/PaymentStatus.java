package com.example.foodpg.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {
    NEW(PaymentStatusType.PENDING, "new"), //Nowa, nieobsłużona transakcja
    PAYMENT_AUTHORIZED(PaymentStatusType.SUCCESS, "authorized"), //Autoryzacja transakcji
    PENDING(PaymentStatusType.PENDING, "pending"), //Oczekiwanie na status
    SUBMITTED(PaymentStatusType.PENDING, "submitted"), //Wysłana do realizacji
    REJECTED(PaymentStatusType.FAILURE, "rejected"), //Transakcja odrzucona
    SETTLED(PaymentStatusType.SUCCESS, "settled"), //Transakcja zrealizowana
    ERROR(PaymentStatusType.FAILURE, "error"), //Błąd w transakcji
    CANCELED(PaymentStatusType.FAILURE, "cancelled"); //Transakcja anulowana

    private PaymentStatusType paymentStatusType;
    private String paymentStatusName;
    private static final Map<String, PaymentStatus> paymentStatusEnumMap = new HashMap<>();

    static {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            paymentStatusEnumMap.put(paymentStatus.getPaymentStatusName(), paymentStatus);
        }
    }

    PaymentStatus(PaymentStatusType paymentStatusType,
                  String paymentStatusName) {
        this.paymentStatusType = paymentStatusType;
        this.paymentStatusName = paymentStatusName;
    }

    public PaymentStatusType getPaymentStatusType() {
        return paymentStatusType;
    }

    public String getPaymentStatusName() {
        return paymentStatusName;
    }

    public static PaymentStatus getPaymentStatusByStatusName(String paymentStatusName) {
        return paymentStatusEnumMap.get(paymentStatusName);
    }
}
