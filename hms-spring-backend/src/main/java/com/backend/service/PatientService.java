package com.backend.service;

import java.util.Optional;

import com.backend.dtos.PatientDTO;
import com.backend.entity.Patient;

public interface PatientService {
	
	 Optional<PatientDTO> getPatientDetailsByUserId(Long userId);

}
