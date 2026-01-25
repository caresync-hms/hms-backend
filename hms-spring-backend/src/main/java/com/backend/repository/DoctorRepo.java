package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dtos.DoctorDTO;
import com.backend.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	   @Query("""
		        SELECT new com.backend.dtos.DoctorDTO(
		            CONCAT(u.firstname, ' ', u.lastname),
		            d.specilization,
		            dept.departmentName,
		            u.phone,
		            u.email,
		            u.gender
		        )
		        FROM Doctor d
		        JOIN d.user u
		        JOIN d.dept dept
		    """)
		    List<DoctorDTO> findAllDoctors();
}
