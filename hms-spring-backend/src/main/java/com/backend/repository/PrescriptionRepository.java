package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.dtos.PrescriptionRespDTO;
import com.backend.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {


	@Query("""
			SELECT new com.backend.dtos.PrescriptionRespDTO(
			    p.id,
			    pat.id,
			    concat(patUser.firstname, ' ', patUser.lastname),
			    doc.id,
			    concat(docUser.firstname, ' ', docUser.lastname),
			    p.medicine,
			    app.id,
			    p.issueDate,
			    p.advice
			)
			FROM Prescription p
			JOIN p.patient pat
			JOIN pat.user patUser
			JOIN p.doctor doc
			JOIN doc.user docUser
			JOIN p.appointment app
			""")
	List<PrescriptionRespDTO> findAllDtos();

	@Query("""
			SELECT new com.backend.dtos.PrescriptionRespDTO(
			    p.id,
			    pat.id,
			    concat(patUser.firstname, ' ', patUser.lastname),
			    doc.id,
			    concat(docUser.firstname, ' ', docUser.lastname),
			    p.medicine,
			    app.id,
			    p.issueDate,
			    p.advice
			)
			FROM Prescription p
			JOIN p.patient pat
			JOIN pat.user patUser
			JOIN p.doctor doc
			JOIN doc.user docUser
			JOIN p.appointment app
			WHERE p.id = :id
			""")
	Optional<PrescriptionRespDTO> findDtoById(@Param("id") Long id);

	@Query("""
			SELECT new com.backend.dtos.PrescriptionRespDTO(
			    p.id,
			    pat.id,
			    concat(patUser.firstname, ' ', patUser.lastname),
			    doc.id,
			    concat(docUser.firstname, ' ', docUser.lastname),
			    p.medicine,
			    app.id,
			    p.issueDate,
			    p.advice
			)
			FROM Prescription p
			JOIN p.patient pat
			JOIN pat.user patUser
			JOIN p.doctor doc
			JOIN doc.user docUser
			JOIN p.appointment app
			WHERE pat.id = :patientId
			""")
	List<PrescriptionRespDTO> findDtosByPatientId(@Param("patientId") Long patientId);

	@Query("""
			SELECT new com.backend.dtos.PrescriptionRespDTO(
			    p.id,
			    pat.id,
			    concat(patUser.firstname, ' ', patUser.lastname),
			    doc.id,
			    concat(docUser.firstname, ' ', docUser.lastname),
			    p.medicine,
			    app.id,
			    p.issueDate,
			    p.advice
			)
			FROM Prescription p
			JOIN p.patient pat
			JOIN pat.user patUser
			JOIN p.doctor doc
			JOIN doc.user docUser
			JOIN p.appointment app
			WHERE doc.id = :doctorId
			""")
	List<PrescriptionRespDTO> findDtosByDoctorId(@Param("doctorId") Long doctorId);

	@Query("""
			SELECT new com.backend.dtos.PrescriptionRespDTO(
			    p.id,
			    pat.id,
			    concat(patUser.firstname, ' ', patUser.lastname),
			    doc.id,
			    concat(docUser.firstname, ' ', docUser.lastname),
			    p.medicine,
			    app.id,
			    p.issueDate,
			    p.advice
			)
			FROM Prescription p
			JOIN p.patient pat
			JOIN pat.user patUser
			JOIN p.doctor doc
			JOIN doc.user docUser
			JOIN p.appointment app
			WHERE app.id = :appointmentId
			""")
	List<PrescriptionRespDTO> findDtosByAppointmentId(@Param("appointmentId") Long appointmentId);
}
