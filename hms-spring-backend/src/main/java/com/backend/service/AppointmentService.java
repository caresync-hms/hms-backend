package com.backend.service;

import java.util.List;

import com.backend.dtos.AdminAppointmentDTO;
//import com.backend.dtos.AddAppointmentDto;
import com.backend.dtos.AppointmentBookingDto;
import com.backend.dtos.AppointmentBookingRequestDto;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.AppointmentUpdateRequestDto;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;

public interface AppointmentService {
	
    List<PatientByDoctorDto> getPatientsByDoctorId(Long doctorId);
    List<AppointmentByPatientDto> getAllAppointmentsByPatientId(Long patientId);
    AppointmentBookingDto bookAppointment(AppointmentBookingRequestDto dto);
    void softDeleteAppointment(Long appointmentId);
    AppointmentBookingDto updateAppointmentDate(AppointmentUpdateRequestDto dto);
    List<AdminAppointmentDTO> getAllAdminAppointments();
    List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId);
    void acceptAppointment(Long appointmentId);

    void rejectAppointment(Long appointmentId);

}
