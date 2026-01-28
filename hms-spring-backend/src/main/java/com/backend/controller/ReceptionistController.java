package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.CreatePatientDTO;
import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.InvoiceRespDTO;
import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientReqDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.dtos.ReceptionistPatientDTO;
import com.backend.service.InvoiceService;
import com.backend.service.PatientService;
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
    private final PatientService patientService;


    /* ===================== INVOICES ===================== */
    
    @GetMapping("/patients")
    public List<PatientRespDTO> getAllPatients() {
        return patientService.getAllPatients();
    }
    
    @PostMapping("/patient")
    public ResponseEntity<ApiResponse> createPatient(
            @RequestBody CreatePatientDTO  dto) {
    	 
    			 PatientDTO patient = patientService.addPatientReceptionist(dto);

    		    return ResponseEntity.ok(
    		        new ApiResponse("Patient created successfully", "SUCCESS", patient)
    		    );
    	    
    }

    // Create invoice for a patient
    @PostMapping("/invoices")
    public ResponseEntity<InvoiceRespDTO> createInvoice(
            @RequestBody InvoiceDTO dto) {

        InvoiceRespDTO invoice = invoiceService.createInvoice(dto);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }
  
   



    // Get all invoices of a patient
    @GetMapping("/patients/{patientId}/invoices")
    public ResponseEntity<List<InvoiceRespDTO>> getInvoicesByPatient(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                invoiceService.getInvoicesByPatient(patientId)
        );
    }

    // Get all invoices (optional but useful)
    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceRespDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    /* ===================== PAYMENTS ===================== */

    // Make payment for an invoice
    @PostMapping("/payments")
    public ResponseEntity<PaymentRespDTO> makePayment(
            @RequestBody PaymentDTO dto) {

        PaymentRespDTO payment = paymentService.makePayment(dto);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    // Get all payments of a patient
    @GetMapping("/patients/{patientId}/payments")
    public ResponseEntity<List<PaymentRespDTO>> getPaymentsByPatient(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByPatient(patientId)
        );
    }

    // Get all payments (optional)
    @GetMapping("/payments")
    public ResponseEntity<List<PaymentRespDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}




