package com.backend.service;

import java.util.List;

import com.backend.dtos.CreatePatientDTO;
import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientIdDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.dtos.ReceptionistPatientDTO;
import com.backend.dtos.UpdatePatientDTO;
import com.backend.entity.Status;

public interface PatientService {

	PatientDTO getPatientByUserId(Long userId);

	PatientDTO addPatient(CreatePatientDTO dto);

	PatientDTO updatePatient(Long patientId, UpdatePatientDTO dto);

	List<PatientRespDTO> getPatientsByDoctorId(Long doctorId);

	List<PatientRespDTO> getAllPatients();

	void updateStatus(Long id, Status status);
	
	//PatientDTO addPatientForExistingUser(ReceptionistPatientDTO dto);
	
	PatientDTO addPatientReceptionist(CreatePatientDTO dto);


}
