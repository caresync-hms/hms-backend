package com.backend.dtos;



import java.math.BigDecimal;
import java.time.LocalDate;

import com.backend.entity.PaymentMethod;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class PaymentRespDTO {
    private Long paymentId;
    private Long invoiceId;
    private Long patientId;
    private String patientName;
    private BigDecimal amount;
    private PaymentMethod method;
    private LocalDate date;
}
