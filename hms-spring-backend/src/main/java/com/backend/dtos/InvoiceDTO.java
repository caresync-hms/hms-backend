package com.backend.dtos;



import java.math.BigDecimal;
import java.time.LocalDate;

import com.backend.entity.PaymentMethod;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InvoiceDTO {
	    private Long patientId;
	  
	    private BigDecimal amount;
	  
	}

