
package com.backend.service;

import java.util.List;

import com.backend.dtos.AppointmentResponseDto;

public interface DoctorService {

    List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId);
}
