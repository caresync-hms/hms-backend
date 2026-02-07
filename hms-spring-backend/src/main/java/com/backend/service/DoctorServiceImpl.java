package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.custom_exceptions.ResourceNotFoundException;
import com.backend.dtos.AddDoctorDTO;
import com.backend.dtos.DoctorDTO;
import com.backend.dtos.UpdateDoctorDTO;
import com.backend.entity.Department;
import com.backend.entity.Doctor;
import com.backend.entity.Role;
import com.backend.entity.Status;
import com.backend.entity.User;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DepartmentRepository;
import com.backend.repository.DoctorRepository;
import com.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private AppointmentRepo appointmentRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DoctorDTO> getAllDoctors() {

		return doctorRepository.findAllDoctors().stream().map(DoctorDTO::new).toList();
	}

	@Override
	public DoctorDTO getDoctorById(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

		return new DoctorDTO(doctor);
	}

	@Override
	public void updateDoctorStatus(Long doctorId, Status status) {

		User user = doctorRepository.findUserByDoctorId(doctorId);

		if (user.getStatus() == status) {
			return;
		}

		user.setStatus(status);

	}

	@Override
	public DoctorDTO addDoctor(AddDoctorDTO dto) {

		
		User user = User.builder().firstname(dto.getFirstname()).lastname(dto.getLastname()).email(dto.getEmail())
				.phone(dto.getPhone()).gender(dto.getGender()).dob(dto.getDob())
				.password(passwordEncoder.encode(dto.getPassword()))
				.status(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE).role(Role.ROLE_DOCTOR).build();

		userRepository.save(user);

		
		Department department = departmentRepository.findByDepartmentName(dto.getDepartmentName())
				.orElseThrow(() -> new RuntimeException("Department not found: " + dto.getDepartmentName()));

		
		Doctor doctor = Doctor.builder().user(user).specialization(dto.getSpecialization()).dept(department).build();

		doctorRepository.save(doctor);

		return new DoctorDTO(doctor);
	}

	@Override
	public DoctorDTO updateDoctor(Long doctorId, UpdateDoctorDTO dto) {

		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));

		
		doctor.setSpecialization(dto.getSpecialization());

		Department department = departmentRepository.findByDepartmentName(dto.getDepartmentName())
				.orElseThrow(() -> new RuntimeException("Department not found: " + dto.getDepartmentName()));
		doctor.setDept(department);

		User user = doctor.getUser();
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setPhone(dto.getPhone());
		user.setGender(dto.getGender());
		user.setDob(dto.getDob());
		user.setStatus(dto.getStatus());

		
		return new DoctorDTO(doctor);
	}

	@Override
	public DoctorDTO getDoctorByUserId(Long userId) {

		Doctor doctor = doctorRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Doctor not found for userId: " + userId));

		return new DoctorDTO(doctor);
	}

}
