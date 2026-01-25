package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dtos.PatientDTO;
import com.backend.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	 Optional<Patient> findByUser_Id(Long userId);
	 @Query("""
		        SELECT DISTINCT a.patient
		        FROM Appointment a
		        WHERE a.doctor.id = :doctorId
		    """)
		    List<Patient> findPatientsByDoctorId(Long doctorId);
}
