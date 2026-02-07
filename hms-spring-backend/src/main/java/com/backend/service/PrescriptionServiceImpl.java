package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dtos.PrescriptionReqDTO;
import com.backend.dtos.PrescriptionRespDTO;
import com.backend.dtos.PrescriptionUpdateDTO;
import com.backend.entity.Appointment;
import com.backend.entity.Doctor;
import com.backend.entity.Patient;
import com.backend.entity.Prescription;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DoctorRepository;
import com.backend.repository.PatientRepository;
import com.backend.repository.PrescriptionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

	private final PrescriptionRepository prescriptionRepository;
	private final PatientRepository patientRepository;
	private final AppointmentRepo appointmentRepository;
	private final DoctorRepository doctorRepository;



	@Override
	public PrescriptionRespDTO createPrescription(PrescriptionReqDTO dto) {

		Patient patient = patientRepository.findById(dto.getPatientId())
				.orElseThrow(() -> new RuntimeException("Patient not found"));

		Doctor doctor = doctorRepository.findById(dto.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor not found"));

		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		Prescription prescription = new Prescription();
		prescription.setMedicine(dto.getMedicane());
		prescription.setAdvice(dto.getNotes());
		prescription.setIssueDate(dto.getDateIssued());
		prescription.setPatient(patient);
		prescription.setDoctor(doctor);
		prescription.setAppointment(appointment);

		prescriptionRepository.save(prescription);

		
		return prescriptionRepository.findDtoById(prescription.getId()).orElseThrow();
	}



	@Override
	public PrescriptionRespDTO getPrescriptionById(Long id) {
		return prescriptionRepository.findDtoById(id).orElseThrow(() -> new RuntimeException("Prescription not found"));
	}

	@Override
	public List<PrescriptionRespDTO> getAllPrescriptions() {
		return prescriptionRepository.findAllDtos();
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByPatient(Long patientId) {
		return prescriptionRepository.findDtosByPatientId(patientId);
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByDoctor(Long doctorId) {
		return prescriptionRepository.findDtosByDoctorId(doctorId);
	}

	@Override
	public List<PrescriptionRespDTO> getPrescriptionByAppointment(Long appointmentId) {
		return prescriptionRepository.findDtosByAppointmentId(appointmentId);
	}

	

	@Override
	public PrescriptionRespDTO updatePrescription(Long id, PrescriptionUpdateDTO dto) {

		Prescription prescription = prescriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Prescription not found"));

		prescription.setMedicine(dto.getMedicine());
		prescription.setAdvice(dto.getNotes());

		prescriptionRepository.save(prescription);

		return prescriptionRepository.findDtoById(id).orElseThrow();
	}

	

	@Override
	public void deletePrescription(Long id) {
		prescriptionRepository.deleteById(id);
	}
}
