package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.dtos.PrescriptionRespDTO;
import com.backend.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
	@Query("""
		       SELECT p
		       FROM Prescription p
		       WHERE p.patient.Id =:patientId
		       """)
		List<Prescription> findByPatientId(@Param("patientId") Long patientId);
}
