package com.backend.service;

import java.util.List;

import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;

public interface PrescriptionService {
PrescriptionRespDTO createPrescription(PrescriptionReqDTO dto);
	
	PrescriptionRespDTO getPrescriptionById(Long id);
	
	List<PrescriptionRespDTO> getAllPrescriptions();
	
	List<PrescriptionRespDTO> getPrescriptionByPatient(Long patientId);
	
	List<PrescriptionRespDTO> getPrescriptionByDoctor(Long doctorId);
	
	void deletePrescription(Long id);
}
