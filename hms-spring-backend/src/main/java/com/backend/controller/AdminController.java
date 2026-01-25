//package com.backend.controller;
//
//public class AdminController {
//
//}

package com.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AdminAppointmentDTO;
import com.backend.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/appointments")
@RequiredArgsConstructor
public class AdminController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AdminAppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(
            appointmentService.getAllAdminAppointments()
        );
    }
}
