package com.backend.service;

import java.util.Optional;
import com.backend.repository.AppointmentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.entity.Appointment;

@Service
@Transactional

public class DoctorServiceImpl implements DoctorService  {
@Autowired 
public AppointmentRepo appointment; 
	@Override
	public Optional<Appointment> getPatientDetailsByDoctorId(Long userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
