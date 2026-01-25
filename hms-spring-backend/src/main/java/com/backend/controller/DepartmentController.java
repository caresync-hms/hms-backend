package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PatchExchange;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.DepartmentReqDTO;
import com.backend.dtos.DepartmentRespDTO;
import com.backend.service.DepartmentService;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<?>addDepartment(@RequestBody DepartmentReqDTO dto){
		try {
			return ResponseEntity.ok(departmentService.addDepartment(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllDepartment(){
		try {
			return ResponseEntity.ok(departmentService.getAllDepartments());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeDepartment(@PathVariable Long id){
		try {
			departmentService.removeDepartment(id);
			return ResponseEntity.ok(new ApiResponse("Department Deleted Successfully", "SUCESS"));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
 
}