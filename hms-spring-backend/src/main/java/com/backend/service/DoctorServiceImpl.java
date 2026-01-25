package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.custom_exceptions.ResourceNotFoundException;
import com.backend.dtos.DoctorDTO;
import com.backend.entity.Doctor;
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

		System.out.println("this is user ---> " + user.getStatus());

		if (user.getStatus() == status) {
			return;
		}

		user.setStatus(status);

	}

}
