package com.backend.service;

import java.util.List;

import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.entity.Payment;

public interface PaymentService {
	    Payment makePayment(PaymentDTO dto);
	  //  InvoiceDTO generateInvoice(Integer paymentId);
	    List<PaymentRespDTO> getAllPayments();
	    List<Payment> getPaymentsByPatient(Long patientId);
	}

