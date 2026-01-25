package com.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.AppointmentResponseDto;
import com.backend.dtos.DoctorDTO;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DoctorRepo;
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
    @Override
    public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findAppointmentsByDoctorId(doctorId);
    }
    
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepo.findAllDoctors();
    }
}

