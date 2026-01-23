package com.backend.service;

import java.util.Optional;

import com.backend.entity.Appointment;

public interface  DoctorService {
	Optional<Appointment> getPatientDetailsByDoctorId(Long userId);
}
