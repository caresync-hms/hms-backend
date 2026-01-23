package com.backend.dtos;

import java.time.LocalDateTime;

import com.backend.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentResponseDto {

//    private Long appointmentId;
//    private LocalDateTime dateOfAppointment;
//    private Status status;
//
//    private Long doctorId;
//    private Long patientId;
	
	private LocalDateTime dateOfAppointment;
    private Status appointmentStatus;
    private String patientName;
}

