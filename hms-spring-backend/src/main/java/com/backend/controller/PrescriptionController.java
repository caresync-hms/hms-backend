package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;
import com.backend.dtos.PrescriptionUpdateDTO;
import com.backend.service.PrescriptionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/prescriptions")
@AllArgsConstructor
public class PrescriptionController {
	@Autowired
	private final PrescriptionService prescriptionService;

	@PostMapping
	public ResponseEntity<?> createPrescription(@RequestBody PrescriptionReqDTO dto) {
		try {
			return ResponseEntity.ok(prescriptionService.createPrescription(dto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPrescriptionById (@PathVariable Long id) {
		try {
			return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPrescription(){
		try {
			return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<?> getPrescriptionByPatientId(@PathVariable Long id){
		try {
			return ResponseEntity.ok(prescriptionService.getPrescriptionByPatient(id));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}	
	}
	
	@GetMapping("/doctor/{id}")
	public ResponseEntity<?> getPrescriptionByDoctorId(@PathVariable Long id){
		try {
			return ResponseEntity.ok(prescriptionService.getPrescriptionByDoctor(id));
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<?> getPrescriptionByAppointmentId(@PathVariable Long id){
		try {
			return ResponseEntity.ok(prescriptionService.getPrescriptionByAppointment(id));
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePrescription(@PathVariable Long id) {
		try {
			 prescriptionService.deletePrescription(id);
		        return ResponseEntity.ok(
		                new ApiResponse("Prescription deleted successfully", "SUCCESS"));
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePrescription(@PathVariable Long id,@RequestBody PrescriptionUpdateDTO dto) {

	    try {
	        return ResponseEntity.ok(
	                prescriptionService.updatePrescription(id, dto)
	        );
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse(e.getMessage(), "FAILED"));
	    }
	}

	
	
}
