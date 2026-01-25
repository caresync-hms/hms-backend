package com.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.backend.dtos.AddAppointmentDto;
import com.backend.dtos.ApiResponse;
import com.backend.dtos.DoctorDTO;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;
import com.backend.service.AppointmentService;
import com.backend.service.DoctorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
@RestController 
@RequestMapping("/doctor")

public class DoctorController {
	@Autowired
	public DoctorService doctorService ;
	
	@Autowired
	 private  AppointmentService appointmentService;
	@GetMapping("/{doctorId}/appointments")
	public ResponseEntity<?> getAppointments(@PathVariable Long doctorId) {
	    return ResponseEntity.ok(
	        doctorService.getAppointmentsByDoctorId(doctorId)
	    );
	}
  
	   @GetMapping("/doctor/{doctorId}/patients")
	    public ResponseEntity<List<PatientByDoctorDto>> getPatientsByDoctorId(
	            @PathVariable Long doctorId) {

	        return ResponseEntity.ok(
	                appointmentService.getPatientsByDoctorId(doctorId)
	        );
	    }
	

	   @GetMapping("/doctors/all")
	   public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
	       return ResponseEntity.ok(
	               doctorService.getAllDoctors()
	       );
	   }


}
