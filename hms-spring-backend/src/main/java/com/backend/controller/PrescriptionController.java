package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;
import com.backend.service.PrescriptionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/prescription")
@AllArgsConstructor
public class PrescriptionController {
	@Autowired
	private final PrescriptionService prescriptionService;

	@PostMapping
	public PrescriptionRespDTO createPrescription(@RequestBody PrescriptionReqDTO dto) {
		return prescriptionService.createPrescription(dto);
	}
	
	@GetMapping("/{id}")
	public PrescriptionRespDTO getPrescriptionById (@PathVariable Long id) {
		return prescriptionService.getPrescriptionById(id);
	}
	
	@GetMapping
	public List<PrescriptionRespDTO> getAllPrescription(){
		return prescriptionService.getAllPrescriptions();
	}
	
	@GetMapping("/patient/{id}")
	public List<PrescriptionRespDTO> getPrescriptionByPatientId(@PathVariable Long id){
	
		return prescriptionService.getPrescriptionByPatient(id);
	}
}
