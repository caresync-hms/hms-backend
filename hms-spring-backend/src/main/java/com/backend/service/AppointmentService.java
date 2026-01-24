package com.backend.service;

import java.util.List;

import com.backend.dtos.AddAppointmentDto;
import com.backend.dtos.AppointmentBookingDto;
import com.backend.dtos.AppointmentBookingRequestDto;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;

public interface AppointmentService {

    Appointment addAppointment(AddAppointmentDto dto);
    List<PatientByDoctorDto> getPatientsByDoctorId(Long doctorId);
    List<AppointmentByPatientDto> getAllAppointmentsByPatientId(Long patientId);
    AppointmentBookingDto bookAppointment(AppointmentBookingRequestDto dto);
}
