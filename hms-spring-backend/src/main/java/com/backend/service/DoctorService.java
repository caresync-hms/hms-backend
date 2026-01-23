package com.backend.service;

import java.util.Optional;

import com.backend.entity.Appointment;
import java.util.List;
public interface  DoctorService {
	List<Appointment> getAppointmentDetailsByDoctorId(Long userId);
}
