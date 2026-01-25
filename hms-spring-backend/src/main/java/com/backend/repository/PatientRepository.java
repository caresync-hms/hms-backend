package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.entity.Patient;
import com.backend.entity.User;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findByUser_Id(Long userId);

	@Query("""
			    SELECT DISTINCT a.patient
			    FROM Appointment a
			    WHERE a.doctor.id = :doctorId
			""")
	List<Patient> findPatientsByDoctorId(Long doctorId);

	@Query("""
			    select p.user
			    from Patient p
			    where p.id = :patientId
			""")
	User findUserByPatientId(@Param("patientId") Long patientId);

}
