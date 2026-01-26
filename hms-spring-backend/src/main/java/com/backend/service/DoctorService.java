
package com.backend.service;

import java.util.List;

import com.backend.dtos.AddDoctorDTO;
import com.backend.dtos.DoctorDTO;
import com.backend.dtos.UpdateDoctorDTO;
import com.backend.entity.Status;

public interface DoctorService {

	List<DoctorDTO> getAllDoctors();

	void updateDoctorStatus(Long doctorId, Status status);

	DoctorDTO getDoctorById(Long doctorId);

	DoctorDTO addDoctor(AddDoctorDTO dto);

	DoctorDTO updateDoctor(Long doctorId, UpdateDoctorDTO dto);

}
