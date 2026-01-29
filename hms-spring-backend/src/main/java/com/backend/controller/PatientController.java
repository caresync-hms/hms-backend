package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.CreatePatientDTO;
import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientIdDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.dtos.StatusUpdateDTO;
import com.backend.dtos.UpdatePatientDTO;
import com.backend.entity.PaymentMethod;
import com.backend.service.PatientService;
import com.backend.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
	@Autowired
	private final PatientService patientService;
	@Autowired
	private final PaymentService paymentService;

	@GetMapping
	public ResponseEntity<?> getAllPatients() {
		try {
			return ResponseEntity.ok(patientService.getAllPatients());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getPatientDetails(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(patientService.getPatientByUserId(userId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@PostMapping({ "", "/register" })
	public ResponseEntity<PatientDTO> addPatient(@RequestBody CreatePatientDTO dto) {
		return ResponseEntity.ok(patientService.addPatient(dto));
	}

	@GetMapping("doctor/{doctorId}")
	public ResponseEntity<?> getPatientByDoctor(@PathVariable Long doctorId) {
		try {
			return ResponseEntity.ok(patientService.getPatientByUserId(doctorId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@PutMapping("/{patientId}")
	public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientDTO dto) {
		return ResponseEntity.ok(patientService.updatePatient(patientId, dto));
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<Void> updatePatientStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto) {

		patientService.updateStatus(id, dto.getStatus());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<?> getPatientByUserId(@PathVariable Long userId) {

        if (userId == null || userId <= 0) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("Invalid userId", "Failed"));
        }

        return ResponseEntity.ok(patientService.getPatientByUserId(userId));
    }
	
	

   
    @GetMapping("/{patientId}/payments")
    public ResponseEntity<List<PaymentRespDTO>> getPaymentsByPatient(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByPatient(patientId)
        );
    }

   
    @PutMapping("/payments/{invoiceId}")
    public ResponseEntity<PaymentRespDTO> payInvoice(
            @PathVariable Long invoiceId) {

        PaymentDTO dto = new PaymentDTO();
        dto.setInvoiceId(invoiceId);
        dto.setPaymentMethod(PaymentMethod.UPI);

        PaymentRespDTO payment = paymentService.makePayment(dto);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    
    @GetMapping("/payments/{paymentId}/receipt")
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable Long paymentId) {

        byte[] pdf = paymentService.generateReceipt(paymentId);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header(
                    "Content-Disposition",
                    "attachment; filename=receipt_" + paymentId + ".pdf"
                )
                .body(pdf);
    }


}
