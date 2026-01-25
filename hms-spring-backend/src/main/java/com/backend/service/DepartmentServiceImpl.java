package com.backend.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.DepartmentReqDTO;
import com.backend.dtos.DepartmentRespDTO;
import com.backend.entity.Department;
import com.backend.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DepartmentRespDTO addDepartment(DepartmentReqDTO dto) {
		if(departmentRepository.existsByDepartmentName(dto.getDepartmentName())) {
			throw new RuntimeException("Department already exists");
		}
		Department department=modelMapper.map(dto, Department.class);
		Department saved=departmentRepository.save(department);
		return modelMapper.map(saved,DepartmentRespDTO.class);
	}

	@Override
	public List<DepartmentRespDTO> getAllDepartments() {
		
		return departmentRepository.findAll().stream().map(dept->modelMapper.map(dept,DepartmentRespDTO.class)).toList();
	}

	@Override
	public void removeDepartment(Long id) {
		Department department=departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
		departmentRepository.delete(department);
		
	}


}
