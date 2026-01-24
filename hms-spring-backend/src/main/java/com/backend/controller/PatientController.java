package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.PatientReqDTO;
import com.backend.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
	@Autowired
	private final PatientService patientService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getPatientDetails(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(patientService.getPatientDetailsByUserId(userId));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addPatient(@RequestBody PatientReqDTO dto) {
		try {
			return ResponseEntity.ok(patientService.addPatient(dto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
}
