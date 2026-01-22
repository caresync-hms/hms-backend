package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.dtos.PatientDTO;
import com.backend.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	PatientDTO findByPatientId(Long userId);
}
