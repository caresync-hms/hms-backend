package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	
}
