package com.backend.service;

import com.backend.dtos.PatientDTO;

public interface PatientService {
	
	 PatientDTO getPatientDetailsByUserId(Long userId);

}
