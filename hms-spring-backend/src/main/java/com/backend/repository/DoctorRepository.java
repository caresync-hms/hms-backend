package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.dtos.DoctorByUserDto;
import com.backend.entity.Doctor;
import com.backend.entity.User;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Optional<Doctor> findByUserId(Long userId);

	@Query("""
			    SELECT d
			    FROM Doctor d
			    JOIN FETCH d.user
			    JOIN FETCH d.dept
			""")
	List<Doctor> findAllDoctors();

	@Query("""
			   SELECT d.user
			   FROM Doctor d
			   WHERE d.id = :doctorId
			""")
	User findUserByDoctorId(@Param("doctorId") Long doctorId);
	
	@Query("""
	        SELECT new com.backend.dtos.DoctorByUserDto(
			    d.id,
	            d.dept.departmentName,
	            d.specialization,
	            CONCAT(u.firstname, ' ', u.lastname),
	            u.phone,
	            u.email,
	            u.dob,
	            u.gender,
	            u.address
	        )
	        FROM Doctor d
	        JOIN d.user u
	        WHERE u.id = :userId
	    """)
	    DoctorByUserDto findDoctorByUserId(@Param("userId") Long userId);
}
