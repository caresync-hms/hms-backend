package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.AssignBedDTO;
import com.backend.dtos.BedDTO;
import com.backend.dtos.BedRespDTO;
import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.InvoiceRespDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.dtos.WardDTO;
import com.backend.entity.InvoiceStatus;
import com.backend.service.BedService;
import com.backend.service.InvoiceService;
import com.backend.service.PatientService;
import com.backend.service.PaymentService;
import com.backend.service.WardService;

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
	private final BedService bedService;
	 private final WardService wardService;
	    

    /* ===================== INVOICES ===================== */

    // Create invoice for a patient
    @PutMapping("/invoices/{invoiceId}/status")
    public ResponseEntity<InvoiceRespDTO> updateInvoiceStatus(
            @PathVariable Long invoiceId,
            @RequestParam InvoiceStatus status) {

        InvoiceRespDTO updatedInvoice =
                invoiceService.updateStatus(invoiceId, status);

        return ResponseEntity.ok(updatedInvoice);
    }


    
    @GetMapping("/patients")
    public ResponseEntity<List<PatientRespDTO>> getAllPatientsForReceptionist() {

        return ResponseEntity.ok( patientService.getAllPatients());
    }
    
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
    	System.out.println("DTO RECEIVED: " + dto.getPaymentMethod());
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
    @GetMapping(value = "/payments/{paymentId}/receipt", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable Long paymentId) {

        byte[] pdf = paymentService.generateReceipt(paymentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=payment_" + paymentId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }


  
    
   

    // ✅ GET ALL WARDS
    @GetMapping("/wards")
    public List<WardDTO> getAllWards() {
        return wardService.getAllWards();
    }

    // ✅ GET BEDS BY WARD
    @GetMapping("/wards/{wardId}/beds")
    public List<BedRespDTO> getBedsByWard(@PathVariable Long wardId) {
        return bedService.getBedsByWard(wardId);
    }

    // ✅ ASSIGN BED
    @PostMapping("/assign-bed")
    public ResponseEntity<String> assignBed(@RequestBody AssignBedDTO dto) {
        bedService.assignBedToPatient(dto);
        return ResponseEntity.ok("Bed assigned successfully");
    }
    
}




