
package com.backend.service;

import java.util.List;

import com.backend.dtos.DoctorDTO;
import com.backend.entity.Status;

public interface DoctorService {

	List<DoctorDTO> getAllDoctors();

	void updateDoctorStatus(Long doctorId, Status status);

	DoctorDTO getDoctorById(Long doctorId);

}
