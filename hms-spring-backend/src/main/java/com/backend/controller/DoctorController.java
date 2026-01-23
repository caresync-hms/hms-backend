package com.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.ApiResponse;
import com.backend.entity.Appointment;
import com.backend.service.DoctorService;
@RestController 
@RequestMapping("/doctor")

public class DoctorController {
	@Autowired
	public DoctorService doctorService ;
//	@GetMapping("/{userid}")
//	public ResponseEntity<?> getPatientDetails(@PathVariable Long userid){
//		try {
//			return ResponseEntity.ok(doctorService.getAppointmentDetailsByDoctorId(userid));
//		}catch(RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(new ApiResponse(e.getMessage(),"Failed"));
//		}
//	}
	
	  @GetMapping("/{doctorId}/appointments")
	    public ResponseEntity<List<Appointment>> getAppointments(
	            @PathVariable Long doctorId) {

	        return ResponseEntity.ok(
	                doctorService.getAppointmentDetailsByDoctorId(doctorId)
	        );
	    }
}
