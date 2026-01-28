package com.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dtos.CreatePatientDTO;
import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientIdDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.dtos.ReceptionistPatientDTO;
import com.backend.dtos.UpdatePatientDTO;
import com.backend.entity.Patient;
import com.backend.entity.Role;
import com.backend.entity.Status;
import com.backend.entity.User;
import com.backend.repository.PatientRepository;
import com.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	private final PasswordEncoder passwordEncoder;
    
	
	@Override
	public PatientDTO addPatient(CreatePatientDTO dto) {

		/* -------- Email uniqueness check -------- */
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		/* -------- Create User -------- */
		User user = new User();
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setGender(dto.getGender());
		user.setDob(dto.getDob());
		user.setStatus(dto.getStatus());
		user.setRole(Role.ROLE_PATIENT);

		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		 userRepository.saveAndFlush(user);
		 
		 

		/* -------- Create Patient -------- */
		Patient patient = new Patient();
		patient.setUser(user);
		patient.setBloodGroup(dto.getBloodGroup());
		patient.setMedicalHistory(dto.getMedicalHistory());

		 patientRepository.saveAndFlush(patient);
		

		return new PatientDTO(patient);
	}
	@Override
	public PatientDTO addPatientReceptionist(CreatePatientDTO dto) {

		/* -------- Email uniqueness check -------- */
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		/* -------- Create User -------- */
		User user = new User();
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setGender(dto.getGender());
		user.setDob(dto.getDob());
		user.setStatus(Status.ACTIVE);
		user.setRole(Role.ROLE_PATIENT);

		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		 userRepository.saveAndFlush(user);
		 
		 

		/* -------- Create Patient -------- */
		Patient patient = new Patient();
		patient.setUser(user);
		patient.setBloodGroup(dto.getBloodGroup());
		patient.setMedicalHistory(dto.getMedicalHistory());

		 patientRepository.saveAndFlush(patient);
		

		return new PatientDTO(patient);
	}
	@Override
	public List<PatientRespDTO> getAllPatients() {
		return patientRepository.findAll().stream().map(PatientRespDTO::new).toList();
	}

//	@Override
//	public Optional<PatientDTO> getPatientDetailsByUserId(Long userId) {
//
//		return patientRepository.findByUser_Id(userId).map(PatientDTO::new);
//	}

	
	@Override
	public PatientDTO updatePatient(Long patientId, UpdatePatientDTO dto) {

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Patient not found"));

		/* -------- Update Patient fields -------- */
		patient.setBloodGroup(dto.getBloodGroup());
		patient.setMedicalHistory(dto.getMedicalHistory());

		/* -------- Update User fields -------- */
		User user = patient.getUser();
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setPhone(dto.getPhone());
		user.setGender(dto.getGender());
		user.setDob(dto.getDob());
		user.setStatus(dto.getStatus());

		// JPA dirty checking will persist changes
		return new PatientDTO(patient);
	}

	@Override
	public List<PatientRespDTO> getPatientsByDoctorId(Long doctorId) {
		return patientRepository.findPatientsByDoctorId(doctorId).stream()
				.map(p -> modelMapper.map(p, PatientRespDTO.class)).toList();

	}

	@Override
	public void updateStatus(Long patientId, Status status) {

		User user = patientRepository.findUserByPatientId(patientId);

		if (user.getStatus() == status) {
			return;
		}

		user.setStatus(status);

	}

	public PatientDTO getPatientByUserId(Long userId) {

		Patient patient = patientRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Patient not found for userId: " + userId));

		return new PatientDTO(patient);
	}
	
	
	/*public PatientDTO addPatientForExistingUser(ReceptionistPatientDTO dto) {

	    User user = userRepository.findById(dto.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (patientRepository.existsByUser(user)) {
	        throw new RuntimeException("Patient already exists for this user");
	    }

	    Patient patient = new Patient();
	    patient.setUser(user);
	    patient.setBloodGroup(dto.getBloodGroup());
	    patient.setMedicalHistory(dto.getMedicalHistory());

	    patientRepository.save(patient);

	    return new PatientDTO(patient);
	}*/
	

	   
	}



