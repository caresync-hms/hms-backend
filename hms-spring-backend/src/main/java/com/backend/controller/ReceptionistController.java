package com.backend.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.InvoiceRespDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.entity.Invoice;
import com.backend.entity.Payment;
import com.backend.service.InvoiceService;
import com.backend.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/receptionist")
@RequiredArgsConstructor
@Tag(name = "Receptionist Billing API")
public class ReceptionistController {

    private final InvoiceService invoiceService;
    private final PaymentService paymentService;

  
    @PostMapping("/invoices")
    public ResponseEntity<Invoice> generateInvoice(@RequestBody InvoiceDTO dto) {
        Invoice invoice = invoiceService.createInvoice(dto);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    
    @GetMapping("/invoices/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(invoiceId));
    }

    
    @GetMapping("/patients/{patientId}/invoices")
    public ResponseEntity<?> getInvoicesByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(
                invoiceService.getInvoicesByPatient(patientId)
        );
    }
    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceRespDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }


    
    //payments
    
    @PostMapping("/payments")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentDTO dto) {
        Payment payment = paymentService.makePayment(dto);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

   
    @GetMapping("/patients/{patientId}/payments")
    public ResponseEntity<?> getPaymentsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(
                paymentService.getPaymentsByPatient(patientId)
        );
    }
    @GetMapping("/payments")
    public ResponseEntity<List<PaymentRespDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

}


