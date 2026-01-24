package com.backend.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dtos.PatientDTO;
import com.backend.dtos.PatientReqDTO;
import com.backend.dtos.PatientRespDTO;
import com.backend.entity.BloodGroup;
import com.backend.entity.Patient;
import com.backend.entity.User;
import com.backend.repository.PatientRepository;
import com.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
	@Autowired
	private final PatientRepository patientRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	@Override
	public Optional<PatientDTO> getPatientDetailsByUserId(Long userId) {
	
		return patientRepository.findByUser_Id(userId).map(PatientDTO::new);
	}

	@Override
	public PatientRespDTO addPatient(PatientReqDTO dto) {
		
		User user=userRepository.findById(dto.getUserId()).orElseThrow();
		Patient patient =modelMapper.map(dto, Patient.class);
		patient.setUser(user);
		
		Patient savedPatient=patientRepository.save(patient);
		PatientRespDTO resp=modelMapper.map(savedPatient, PatientRespDTO.class);
		resp.setPatientId(savedPatient.getId());
		resp.setUserId(user.getId());
		resp.setFirstname(user.getFirstname());
		resp.setLastname(user.getLastname());
		resp.setEmail(user.getEmail());
		resp.setPhone(user.getPhone());
		resp.setGender(user.getGender());
		resp.setDob(user.getDob().toLocalDate());
		resp.setStatus(user.getStatus());
		resp.setBloodGroup(savedPatient.getBloodGroup());
		resp.setMedicalHistory(savedPatient.getMedicalHistory());
		resp.setAdmitDate(savedPatient.getAdmitDate());
		resp.setDischargeDate(savedPatient.getDischargeDate());
		return resp;
	}
	

}
