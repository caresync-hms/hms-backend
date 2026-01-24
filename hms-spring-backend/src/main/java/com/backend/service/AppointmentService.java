package com.backend.service;

import com.backend.dtos.AddAppointmentDto;
import com.backend.entity.Appointment;

public interface AppointmentService {

    Appointment addAppointment(AddAppointmentDto dto);
}
