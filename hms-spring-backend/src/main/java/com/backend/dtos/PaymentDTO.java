package com.backend.dtos;



import java.math.BigDecimal;

import com.backend.entity.PaymentMethod;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
	public class PaymentDTO {
    private Long invoiceId;
    private Long patientId;
	    private BigDecimal amount;
	    private PaymentMethod paymentMethod;
	}


