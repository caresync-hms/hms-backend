package com.backend.controller;

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
import com.backend.dtos.StatusUpdateDTO;
import com.backend.dtos.UpdatePatientDTO;
import com.backend.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
	@Autowired
	private final PatientService patientService;

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

}
