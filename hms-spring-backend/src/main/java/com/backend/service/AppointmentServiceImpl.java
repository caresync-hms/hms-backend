package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.AddAppointmentDto;
import com.backend.entity.Appointment;
import com.backend.entity.Doctor;
import com.backend.entity.Patient;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DoctorRepo;
import com.backend.repository.PatientRepository;
import com.backend.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
		@Autowired
    private  AppointmentRepo appointmentRepository;
		@Autowired
    private  DoctorRepo doctorRepository;
		@Autowired
    private  PatientRepository patientRepository;

    @Override
    public Appointment addAppointment(AddAppointmentDto dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateOfAppointment(dto.getDateOfAppointment());
        appointment.setStatus(dto.getStatus());

        return appointmentRepository.save(appointment);
    }
}
