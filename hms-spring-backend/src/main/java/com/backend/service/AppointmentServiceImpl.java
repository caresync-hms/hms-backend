

package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.AdminAppointmentDTO;
import com.backend.dtos.AppointmentBookingDto;
import com.backend.dtos.AppointmentBookingRequestDto;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.AppointmentUpdateRequestDto;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;
import com.backend.entity.AppointmentStatus;
import com.backend.entity.Doctor;
import com.backend.entity.Patient;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DoctorRepository;
import com.backend.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepo appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<PatientByDoctorDto> getPatientsByDoctorId(Long doctorId) {
		return appointmentRepository.getPatientsByDoctorId(doctorId);
	}

	@Override
	public List<AppointmentByPatientDto> getAllAppointmentsByPatientId(Long patientId) {
		return appointmentRepository.getAppointmentsByPatientId(patientId);
	}

	// BOOK APPOINTMENT
	@Override
	public AppointmentBookingDto bookAppointment(AppointmentBookingRequestDto dto) {

		Doctor doctor = doctorRepository.findById(dto.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor not found"));

		Patient patient = patientRepository.findById(dto.getPatientId())
				.orElseThrow(() -> new RuntimeException("Patient not found"));

		boolean isBooked = appointmentRepository.existsByDoctor_IdAndDateOfAppointment(dto.getDoctorId(),
				dto.getDateOfAppointment());

		if (isBooked) {
			throw new RuntimeException("Doctor is not available at this time");
		}

		Appointment appointment = new Appointment();
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setDateOfAppointment(dto.getDateOfAppointment());
		appointment.setStatus(AppointmentStatus.PENDING);

		Appointment saved = appointmentRepository.save(appointment);

		AppointmentBookingDto response = new AppointmentBookingDto();
		response.setPatientId(saved.getPatient().getId());
		response.setDoctorName(
				saved.getDoctor().getUser().getFirstname() + " " + saved.getDoctor().getUser().getLastname());
		response.setDoctorSpecialization(saved.getDoctor().getSpecialization());
		response.setDateOfApp(saved.getDateOfAppointment());
		response.setStatus(saved.getStatus());

		return response;
	}

	// CANCEL APPOINTMENT (SOFT DELETE)
	@Override
	public void softDeleteAppointment(Long appointmentId) {

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		appointment.setStatus(AppointmentStatus.CANCELLED);
		appointmentRepository.save(appointment);
	}

	@Override
	public AppointmentBookingDto updateAppointmentDate(AppointmentUpdateRequestDto dto) {

		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		appointment.setDateOfAppointment(dto.getDateOfAppointment());

		Appointment updated = appointmentRepository.save(appointment);

		AppointmentBookingDto response = new AppointmentBookingDto();
		response.setPatientId(updated.getPatient().getId());
		response.setDoctorSpecialization(updated.getDoctor().getSpecialization());
		response.setDateOfApp(updated.getDateOfAppointment());
		response.setStatus(updated.getStatus());

		if (updated.getDoctor().getUser() != null) {
			response.setDoctorName(
					updated.getDoctor().getUser().getFirstname() + " " + updated.getDoctor().getUser().getLastname());
		}

		return response;
	}

	@Override
	public List<AdminAppointmentDTO> getAllAdminAppointments() {
		return appointmentRepository.findAllAdminAppointments();
	}

	@Override
	public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {

		// ✅ CHECK: Doctor exists or not
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

		// ✅ Fetch appointments
		return appointmentRepository.findAppointmentsByDoctorId(doctor.getId());
	}

	@Override
	public void acceptAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		appointment.setStatus(AppointmentStatus.SCHEDULED);
		appointmentRepository.save(appointment);
	}

	@Override
	public void rejectAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		appointment.setStatus(AppointmentStatus.CANCELLED);
		appointmentRepository.save(appointment);
	}
}
