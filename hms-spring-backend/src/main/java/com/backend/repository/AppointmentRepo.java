//
//package com.backend.repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.backend.dtos.AdminAppointmentDTO;
//import com.backend.dtos.AppointmentByPatientDto;
//import com.backend.dtos.AppointmentResponseDto;
//import com.backend.dtos.PatientByDoctorDto;
//import com.backend.entity.Appointment;
//
//public interface AppointmentRepo extends JpaRepository<Appointment, Long> {	
//	@Query("""
//		    SELECT new com.backend.dtos.AppointmentResponseDto(
//		        a.dateOfAppointment,
//		        a.status,
//		        CONCAT(u.firstname, ' ', u.lastname)
//		    )
//		    FROM Appointment a
//		    JOIN a.patient p
//		    JOIN p.user u
//		    WHERE a.doctor.id = :doctorId
//		""")
//    List<AppointmentResponseDto> findAppointmentsByDoctorId(
//            @Param("doctorId") Long doctorId
//    );
//	
//	
//
//	// getpatientbydoctorid
//	
//	@Query("""
//	        SELECT new com.backend.dtos.PatientByDoctorDto(
//	            p.id,
//	            u.id,
//	            u.firstname,
//	            u.lastname,
//	            a.status
//	        )
//	        FROM Appointment a
//	        JOIN a.patient p
//	        JOIN p.user u
//	        WHERE a.doctor.id = :doctorId
//	    """)
//	    List<PatientByDoctorDto>getPatientsByDoctorId (@Param("doctorId") Long doctorId);
//	
//	
//	  @Query("""
//		        SELECT new com.backend.dtos.AppointmentByPatientDto(
//		            CONCAT(d.user.firstname, ' ', d.user.lastname),
//		            d.specialization,
//		            a.dateOfAppointment,
//		            a.status
//		        )
//		        FROM Appointment a
//		        JOIN a.doctor d
//		        JOIN a.patient p
//		        WHERE p.id = :patientId
//		    """)
//		    List<AppointmentByPatientDto> getAppointmentsByPatientId(
//		            @Param("patientId") Long patientId
//		    );
//	  
//	  boolean existsByDoctor_IdAndDateOfAppointment(Long doctorId, LocalDateTime dateOfAppointment);
//	  
//	  boolean existsByDoctor_IdAndDateOfAppointmentAndIdNot(
//		        Long doctorId,
//		        LocalDateTime dateOfAppointment,
//		        Long id
//		);
//	  
//	  @Query("""
//		        SELECT new com.backend.dtos.AdminAppointmentDTO(
//		            CONCAT(d.user.firstname, ' ', d.user.lastname),
//		            CONCAT(p.user.firstname, ' ', p.user.lastname),
//		            a.dateOfAppointment,
//		            a.status,
//		            d.dept.departmentName
//		        )
//		        FROM Appointment a
//		        JOIN a.doctor d
//		        JOIN a.patient p
//		    """)
//		    List<AdminAppointmentDTO> findAllAdminAppointments();
//	 
//	  
//	
//}

package com.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.dtos.AdminAppointmentDTO;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

//    @Query("""
//        SELECT new com.backend.dtos.AppointmentResponseDto(
//            a.dateOfAppointment,
//            a.status,
//            CONCAT(u.firstname, ' ', u.lastname)
//        )
//        FROM Appointment a
//        JOIN a.patient p
//        JOIN p.user u
//        WHERE a.doctor.id = :doctorId
//    """)
//    List<AppointmentResponseDto> findAppointmentsByDoctorId(
//            @Param("doctorId") Long doctorId
//    );

	@Query("""
			    SELECT new com.backend.dtos.AppointmentResponseDto(
			        a.dateOfAppointment,
			        a.status,
			        CONCAT(u.firstname, ' ', u.lastname),
			        u.phone,
			        u.gender
			    )
			    FROM Appointment a
			    JOIN a.patient p
			    JOIN p.user u
			    WHERE a.doctor.id = :doctorId
			""")
	List<AppointmentResponseDto> findAppointmentsByDoctorId(@Param("doctorId") Long doctorId);

//	@Query("""
//			    SELECT new com.backend.dtos.PatientByDoctorDto(
//			        p.id,
//			        u.firstname,
//			        u.lastname,
//			        u.phone,
//			        u.gender,
//			        a.dateOfAppointment,
//			        a.status
//			    )
//			    FROM Appointment a
//			    JOIN a.patient p
//			    JOIN p.user u
//			    WHERE a.doctor.id = :doctorId
//			""")
//	List<PatientByDoctorDto> getPatientsByDoctorId(@Param("doctorId") Long doctorId);

	 @Query("""
		        SELECT DISTINCT new com.backend.dtos.PatientByDoctorDto(
		            u.firstname || ' ' || u.lastname,
		            u.gender,
		            u.dob,
		            u.phone,
		            u.address,
		            p.medicalHistory,
		            p.admitDate,
		            p.dischargeDate
		        )
		        FROM Appointment a
		        JOIN a.patient p
		        JOIN p.user u
		        WHERE a.doctor.id = :doctorId
		    """)
		    List<PatientByDoctorDto> getPatientsByDoctorId(@Param("doctorId") Long doctorId);
	
	
	@Query("""
		    SELECT new com.backend.dtos.AppointmentByPatientDto(
		        a.id,
		        CONCAT(d.user.firstname, ' ', d.user.lastname),
		        d.specialization,
		        a.dateOfAppointment,
		        a.status
		    )
		    FROM Appointment a
		    JOIN a.doctor d
		    JOIN a.patient p
		    WHERE p.id = :patientId
		""")
		List<AppointmentByPatientDto> getAppointmentsByPatientId(
		    @Param("patientId") Long patientId
		);

	boolean existsByDoctor_IdAndDateOfAppointment(Long doctorId, LocalDateTime dateOfAppointment);

	boolean existsByDoctor_IdAndDateOfAppointmentAndIdNot(Long doctorId, LocalDateTime dateOfAppointment, Long id);

	@Query("""
			    SELECT new com.backend.dtos.AdminAppointmentDTO(
			        CONCAT(d.user.firstname, ' ', d.user.lastname),
			        CONCAT(p.user.firstname, ' ', p.user.lastname),
			        a.dateOfAppointment,
			        a.status,
			        d.dept.departmentName
			    )
			    FROM Appointment a
			    JOIN a.doctor d
			    JOIN a.patient p
			""")
	List<AdminAppointmentDTO> findAllAdminAppointments();
}
