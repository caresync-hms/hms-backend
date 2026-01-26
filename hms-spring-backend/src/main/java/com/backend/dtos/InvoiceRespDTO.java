package com.backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class InvoiceRespDTO {
    private Long invoiceId;
    private Long patientId;
    private String patientName;
    private BigDecimal amount;
    private String status;
    private LocalDate createdDate;
}
