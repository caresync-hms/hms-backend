package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;
import com.backend.dtos.PrescriptionUpdateDTO;
import com.backend.entity.Patient;
import com.backend.entity.Prescription;
import com.backend.entity.Doctor;
import com.backend.entity.Appointment;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DoctorRepo;
import com.backend.repository.PatientRepository;
import com.backend.repository.PrescriptionRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@Transactional
@AllArgsConstructor

public class PrescriptionServiceImpl implements PrescriptionService{
	@Autowired
	private final PrescriptionRepository prescriptionRepository;
	private final PatientRepository patientRepository;
	private final AppointmentRepo appointmentRepository;
	private final DoctorRepo doctorRepository;
	private final ModelMapper modelMapper;
	@Override
	public PrescriptionRespDTO createPrescription(PrescriptionReqDTO dto) {
		Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() ->
        new RuntimeException("Patient NOT found with id = " + dto.getPatientId()));
		Doctor doctor= doctorRepository.findById(dto.getDoctorId()).orElseThrow();
		Appointment appointment=appointmentRepository.findById(dto.getAppointmentId()).orElseThrow();
		
		Prescription prescription=new Prescription();
		prescription.setDoctor(doctor);
		prescription.setAppointment(appointment);
		prescription.setPatient(patient);
		prescription.setIssueDate(dto.getDateIssued());
		prescription.setAdvice(dto.getNotes());
		
		Prescription saved =prescriptionRepository.save(prescription);
		
		return mapToRespDTO(saved);
	}

	@Override
	public PrescriptionRespDTO getPrescriptionById(Long id) {
		Prescription prescription=prescriptionRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Prescription NOT found with id = " + id));
		return mapToRespDTO(prescription);
	}

	@Override
	public List<PrescriptionRespDTO> getAllPrescriptions() {
		 return prescriptionRepository.findAll().stream().map(this::mapToRespDTO).toList();
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByPatient(Long patientId) {
		return prescriptionRepository.findByPatientId(patientId).stream().map(this::mapToRespDTO).toList();
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByDoctor(Long doctorId) {
		return prescriptionRepository.findByDoctorId(doctorId).stream().map(this::mapToRespDTO).toList();
	}

	@Override
	public void deletePrescription(Long id) {
		
		Prescription prescription = prescriptionRepository.findById(id).orElseThrow(() ->new RuntimeException("Prescription not found with id: " + id));

	    prescriptionRepository.delete(prescription);
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByAppointment(Long appointmentId) {
		return prescriptionRepository.findByAppointmentId(appointmentId).stream()
	            .map(this::mapToRespDTO)
	            .toList();
	}

	@Override
	public PrescriptionRespDTO updatePrescription(Long id, PrescriptionUpdateDTO dto) {
		Prescription prescription=prescriptionRepository.findById(id).orElseThrow();
		prescription.setAdvice(dto.getNotes());
		prescription.setIssueDate(dto.getIssueDate());
		
		Prescription updated =prescriptionRepository.save(prescription);
		
		return new PrescriptionRespDTO(updated);
	}
	
	private PrescriptionRespDTO mapToRespDTO(Prescription p) {
	    PrescriptionRespDTO dto = new PrescriptionRespDTO();

	    dto.setPrescriptionId(p.getId());

	    dto.setPatientId(p.getPatient().getId());
	    dto.setPatientName(
	        p.getPatient().getUser().getFirstname() + " " + p.getPatient().getUser().getLastname()
	    );

	    dto.setDoctorId(p.getDoctor().getId());
	    dto.setDoctorName(
	        p.getDoctor().getUser().getFirstname()+ " " + p.getDoctor().getUser().getLastname()
	    );

	    dto.setAppointmentId(p.getAppointment().getId());
	    dto.setDateIssued(p.getIssueDate());
	    dto.setNotes(p.getAdvice());

	    return dto;
	}

}
