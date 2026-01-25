
package com.backend.service;

import java.util.List;

import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.DoctorDTO;

public interface DoctorService {

    List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId);
    List<DoctorDTO> getAllDoctors();
}
