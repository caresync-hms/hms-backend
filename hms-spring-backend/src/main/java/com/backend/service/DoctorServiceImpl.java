package com.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.backend.dtos.AddDoctorDto;
import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.DoctorDTO;
import com.backend.dtos.DoctorReqDTO;
import com.backend.dtos.DoctorRespDTO;
import com.backend.entity.Department;
import com.backend.entity.Doctor;
import com.backend.entity.Role;
import com.backend.entity.Status;
import com.backend.entity.User;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DepartmentRepository;
import com.backend.repository.DoctorRepo;
import com.backend.repository.UserRepository;
//import com.backend.repository.AppointmentRepository;
import com.backend.service.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
	@Autowired
    private  AppointmentRepo appointmentRepository;
	@Autowired
	private DoctorRepo doctorRepo;
	 @Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
    
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepo.findAllDoctors();
    }

	@Override
	public DoctorRespDTO addDoctor(DoctorReqDTO dto) {
		User user=userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		Department department=departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
		Doctor doctor=new Doctor();
		doctor.setUser(user);
		doctor.setDept(department);
		doctor.setSpecilization(dto.getSpecialization());
		 Doctor saved=doctorRepo.save(doctor);
		DoctorRespDTO resp=new DoctorRespDTO();
		resp.setDoctorId(saved.getId());
		resp.setSpecialization(saved.getSpecilization());
		resp.setDepartmentName(saved.getDept().getDepartmentName());
		resp.setUserId(saved.getUser().getId());
		resp.setUsername(saved.getUser().getUsername());
		return resp;
	}
    
    
}

