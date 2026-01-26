package com.backend.service;

import java.util.List;
import java.util.Optional;

import com.backend.dtos.CreatePatientDTO;
import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.dtos.UpdatePatientDTO;
import com.backend.entity.Status;

public interface PatientService {

	Optional<PatientDTO> getPatientDetailsByUserId(Long userId);

	PatientDTO addPatient(CreatePatientDTO dto);

	PatientDTO updatePatient(Long patientId, UpdatePatientDTO dto);

	List<PatientRespDTO> getPatientsByDoctorId(Long doctorId);

	List<PatientRespDTO> getAllPatients();

	void updateStatus(Long id, Status status);
}
