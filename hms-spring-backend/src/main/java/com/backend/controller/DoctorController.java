package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AddDoctorDTO;
import com.backend.dtos.DoctorByUserDto;
import com.backend.dtos.DoctorDTO;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.dtos.StatusUpdateDTO;
import com.backend.dtos.UpdateDoctorDTO;
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

	@GetMapping("/{doctorId}/patient")
	public ResponseEntity<List<PatientByDoctorDto>> getPatientsByDoctorId(@PathVariable Long doctorId) {

		return ResponseEntity.ok(appointmentService.getPatientsByDoctorId(doctorId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
		return ResponseEntity.ok(doctorService.getAllDoctors());
	}

	@PostMapping
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody AddDoctorDTO dto) {
		return ResponseEntity.ok(doctorService.addDoctor(dto));
	}

	@PutMapping("/{doctorId}")
	public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorDTO dto) {
		return ResponseEntity.ok(doctorService.updateDoctor(doctorId, dto));
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<Void> updateDoctorStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto) {

		doctorService.updateDoctorStatus(id, dto.getStatus());
		return ResponseEntity.noContent().build();
	}
	
	 @GetMapping("/user/{userId}")
	    public ResponseEntity<DoctorByUserDto> getDoctorByUserId( @PathVariable Long userId) {

	        return ResponseEntity.ok(doctorService.getDoctorByUserId(userId)
	 );
	    }

}
