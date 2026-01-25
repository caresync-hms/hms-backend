package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.Department;
import com.backend.repository.DepartmentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department existing = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new RuntimeException("Department not found"));

		existing.setDepartmentName(department.getDepartmentName());
		existing.setDescription(department.getDescription());

		return departmentRepository.save(existing);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new RuntimeException("Department not found"));

		departmentRepository.delete(department);
	}

}
