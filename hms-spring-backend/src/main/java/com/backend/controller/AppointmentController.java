package com.backend.controller;

import com.backend.dtos.AppointmentBookingDto;
import com.backend.dtos.AppointmentBookingRequestDto;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/patient/{patientId}")
    public List<AppointmentByPatientDto> getAppointmentsByPatientId(
            @PathVariable Long patientId) {
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
    
}
