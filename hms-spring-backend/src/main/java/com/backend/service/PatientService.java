package com.backend.service;

import java.util.List;
import java.util.Optional;

import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientReqDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.entity.Patient;

public interface PatientService {
	
	Optional<PatientDTO> getPatientDetailsByUserId(Long userId);

	PatientRespDTO addPatient(PatientReqDTO dto);
	
	PatientRespDTO updatePatient(Long patientId, PatientReqDTO dto);
	
	List<PatientRespDTO> getPatientsByDoctorId(Long doctorId);
}
