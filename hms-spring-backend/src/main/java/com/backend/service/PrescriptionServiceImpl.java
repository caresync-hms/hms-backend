package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;
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
		
		Prescription persist =prescriptionRepository.save(prescription);
		PrescriptionRespDTO resp= modelMapper.map(persist, PrescriptionRespDTO.class);
		
		resp.setPatientId(patient.getId());
		resp.setPrescriptionId(persist.getId());
		resp.setPatientId(patient.getId());
		resp.setDoctorId(doctor.getId());
		resp.setAppointmentId(appointment.getId());
		resp.setNotes(persist.getAdvice());
		resp.setDateIssued(persist.getIssueDate());
		
		return resp;
	}

	@Override
	public PrescriptionRespDTO getPrescriptionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrescriptionRespDTO> getAllPrescriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByPatient(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByDoctor(Long doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrescription(Long id) {
		// TODO Auto-generated method stub
		
	}

}
