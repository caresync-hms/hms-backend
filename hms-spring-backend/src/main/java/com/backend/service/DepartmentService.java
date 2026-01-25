package com.backend.service;

import java.util.List;

import com.backend.entity.Department;

public interface DepartmentService {

	List<Department> getAllDepartments();

	Department addDepartment(Department department);

	Department updateDepartment(Long departmentId, Department department);

	void deleteDepartment(Long departmentId);
}
