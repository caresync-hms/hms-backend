package com.backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.backend.dtos.DoctorDTO;
import com.backend.dtos.PatientDTO;
import com.backend.dtos.UserRespDTO;
import com.backend.entity.Doctor;
import com.backend.entity.Patient;
import com.backend.entity.Role;
import com.backend.entity.User;
import com.backend.repository.DoctorRepository;
import com.backend.repository.PatientRepository;
import com.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	private final UserRepository userRepository;
	private final ModelMapper mapper;

	@Override
	public Object getProfile(Long userId, String role) {

		switch (Role.valueOf(role)) {

		case ROLE_PATIENT -> {
			Patient patient = patientRepository.findByUserId(userId)
					.orElseThrow(() -> new RuntimeException("Patient profile not found"));
			return new PatientDTO(patient);
		}

		case ROLE_DOCTOR -> {
			Doctor doctor = doctorRepository.findByUserId(userId)
					.orElseThrow(() -> new RuntimeException("Doctor profile not found"));
			return new DoctorDTO(doctor);
		}

		case ROLE_RECEPTIONIST -> {
			User receptionistUser = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("Receptionist profile not found"));

			return mapper.map(receptionistUser, UserRespDTO.class);
		}

		case ROLE_ADMIN -> {
			User adminUser = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("Admin profile not found"));

			return mapper.map(adminUser, UserRespDTO.class);
		}

		default -> throw new RuntimeException("Unsupported role: " + role);
		}
	}
}
