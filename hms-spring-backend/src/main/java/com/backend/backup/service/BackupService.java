package com.backend.backup.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.backup.exporter.DoctorExcelExporter;
import com.backend.backup.exporter.PatientExcelExporter;
import com.backend.entity.Doctor;
import com.backend.entity.Patient;
import com.backend.repository.DoctorRepository;
import com.backend.repository.PatientRepository;

@Service
public class BackupService {

	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;

	public BackupService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	public ByteArrayInputStream backupPatients() {
		List<Patient> patients = patientRepository.findAll();
		return PatientExcelExporter.export(patients);
	}

	public ByteArrayInputStream backupDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		return DoctorExcelExporter.export(doctors);
	}
}
