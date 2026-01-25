package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.DoctorDTO;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.dtos.StatusUpdateDTO;
import com.backend.service.AppointmentService;
import com.backend.service.DoctorService;

@RestController
@RequestMapping("/doctor")

public class DoctorController {
	@Autowired
	public DoctorService doctorService;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/{doctorId}")
	public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long doctorId) {
		return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
	}

	@GetMapping("/{id}/patients")
	public ResponseEntity<List<PatientByDoctorDto>> getPatientsByDoctorId(@PathVariable Long doctorId) {

		return ResponseEntity.ok(appointmentService.getPatientsByDoctorId(doctorId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
		return ResponseEntity.ok(doctorService.getAllDoctors());
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<Void> updateDoctorStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto) {

		doctorService.updateDoctorStatus(id, dto.getStatus());
		return ResponseEntity.noContent().build();
	}

}
