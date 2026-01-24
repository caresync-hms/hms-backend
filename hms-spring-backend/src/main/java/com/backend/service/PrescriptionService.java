package com.backend.service;

import java.util.List;

import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;
import com.backend.dtos.PrescriptionUpdateDTO;

public interface PrescriptionService {
PrescriptionRespDTO createPrescription(PrescriptionReqDTO dto);
	
	PrescriptionRespDTO getPrescriptionById(Long id);
	
	List<PrescriptionRespDTO> getAllPrescriptions();
	
	List<PrescriptionRespDTO> getPrescriptionByPatient(Long patientId);
	
	List<PrescriptionRespDTO> getPrescriptionByDoctor(Long doctorId);
	
	List<PrescriptionRespDTO> getPrescriptionByAppointment(Long appointmentId);
	
	void deletePrescription(Long id);
	
	PrescriptionRespDTO updatePrescription(Long id, PrescriptionUpdateDTO dto);
}
