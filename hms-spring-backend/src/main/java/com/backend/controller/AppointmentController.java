package com.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AdminAppointmentDTO;
import com.backend.dtos.AppointmentBookingDto;
import com.backend.dtos.AppointmentBookingRequestDto;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.AppointmentUpdateRequestDto;
import com.backend.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;

	@GetMapping
	public ResponseEntity<List<AdminAppointmentDTO>> getAllAppointments() {
		return ResponseEntity.ok(appointmentService.getAllAdminAppointments());
	}

	@GetMapping("/patient/{patientId}")
	public List<AppointmentByPatientDto> getAppointmentsByPatientId(@PathVariable Long patientId) {
		return appointmentService.getAllAppointmentsByPatientId(patientId);
	}

	@PostMapping("/book")
	public ResponseEntity<?> bookAppointment(@RequestBody AppointmentBookingRequestDto dto) {
		try {
			AppointmentBookingDto response = appointmentService.bookAppointment(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
		try {
			appointmentService.softDeleteAppointment(appointmentId);
			return ResponseEntity.ok("Appointment cancelled successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update-date")
	public ResponseEntity<?> updateAppointmentDate(@RequestBody AppointmentUpdateRequestDto dto) {
		try {
			AppointmentBookingDto response = appointmentService.updateAppointmentDate(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {

		return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
	}

}
