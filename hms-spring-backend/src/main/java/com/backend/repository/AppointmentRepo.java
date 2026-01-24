
package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.dtos.AppointmentResponseDto;
import com.backend.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

//    @Query("""
//        SELECT new com.backend.dtos.AppointmentResponseDto(
//            a.id,
//            a.dateOfAppointment,
//            a.status,
//            a.doctor.id,
//            a.patient.id
//        )
//        FROM Appointment a
//        WHERE a.doctor.id = :doctorId
//    """)
	
	@Query("""
		    SELECT new com.backend.dtos.AppointmentResponseDto(
		        a.dateOfAppointment,
		        a.status,
		        CONCAT(u.firstname, ' ', u.lastname)
		    )
		    FROM Appointment a
		    JOIN a.patient p
		    JOIN p.user u
		    WHERE a.doctor.id = :doctorId
		""")
    List<AppointmentResponseDto> findAppointmentsByDoctorId(
            @Param("doctorId") Long doctorId
    );
}
