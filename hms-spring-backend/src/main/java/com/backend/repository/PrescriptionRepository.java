package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

}
