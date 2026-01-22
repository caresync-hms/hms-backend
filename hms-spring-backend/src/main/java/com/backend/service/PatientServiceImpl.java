package com.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dtos.PatientDTO;
import com.backend.entity.Patient;
import com.backend.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public PatientDTO getPatientDetailsByUserId(Long userId) {
	
		return patientRepository.findByUserId(userId);
	}

}
