package com.backend.service;

import java.util.List;
import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.entity.Payment;


public interface PaymentService {

    // Make payment against an invoice
    PaymentRespDTO makePayment(PaymentDTO dto);

    // Get all payments of a patient
    List<PaymentRespDTO> getPaymentsByPatient(Long patientId);

    // Get all payments (receptionist/admin)
    List<PaymentRespDTO> getAllPayments();

	byte[] generateReceipt(Long paymentId);
}


