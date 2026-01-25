
package com.backend.service;

import java.util.List;


import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.DoctorDTO;
import com.backend.dtos.DoctorReqDTO;
import com.backend.dtos.DoctorRespDTO;
import com.backend.entity.Doctor;

public interface DoctorService {

	DoctorRespDTO addDoctor(DoctorReqDTO dto);
	
    List<DoctorDTO> getAllDoctors();

}
