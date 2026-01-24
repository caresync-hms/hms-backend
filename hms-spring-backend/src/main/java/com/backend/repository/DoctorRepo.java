package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
