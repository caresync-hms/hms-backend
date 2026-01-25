
package com.backend.service;

import java.util.List;


import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.DoctorDTO;
import com.backend.entity.Doctor;

public interface DoctorService {


    List<DoctorDTO> getAllDoctors();

}
