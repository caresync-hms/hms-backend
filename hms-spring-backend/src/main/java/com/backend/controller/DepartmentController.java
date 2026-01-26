package com.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.ApiResponse;
import com.backend.entity.Department;
import com.backend.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping
	public ResponseEntity<?> getAllDepartments() {
		try {
			return ResponseEntity.ok(departmentService.getAllDepartments());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@PostMapping
	public ResponseEntity<?> addDepartment(@RequestBody Department department) {
		try {
			Department saved = departmentService.addDepartment(department);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@PutMapping("/{departmentId}")
	public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId, @RequestBody Department department) {

		try {
			Department updated = departmentService.updateDepartment(departmentId, department);

			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@DeleteMapping("/{departmentId}")
	public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
		try {
			departmentService.deleteDepartment(departmentId);
			return ResponseEntity.ok(new ApiResponse("Department deleted successfully", "Success"));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
}
