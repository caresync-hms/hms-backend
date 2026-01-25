package com.backend.service;

import java.util.List;

import com.backend.dtos.DepartmentReqDTO;
import com.backend.dtos.DepartmentRespDTO;
import com.backend.entity.Department;

public interface DepartmentService {
	DepartmentRespDTO addDepartment(DepartmentReqDTO dto);
    List<DepartmentRespDTO> getAllDepartments();
    void removeDepartment(Long id);
}
