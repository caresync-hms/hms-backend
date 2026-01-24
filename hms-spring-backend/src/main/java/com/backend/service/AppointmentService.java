package com.backend.service;

import java.util.List;

import com.backend.dtos.AddAppointmentDto;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;

public interface AppointmentService {

    Appointment addAppointment(AddAppointmentDto dto);
    List<PatientByDoctorDto> getPatientsByDoctorId(Long doctorId);
}
