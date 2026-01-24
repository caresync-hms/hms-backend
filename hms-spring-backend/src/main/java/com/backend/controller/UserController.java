package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.backend.dtos.UserReqDTO;
import com.backend.dtos.UserRespDTO;
import com.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		try {
			 return ResponseEntity.ok(userService.getAllUsers());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserReqDTO dto){
		try {
			 return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id){
		try {
			 return ResponseEntity.ok(userService.getUserDetails(id));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(  @PathVariable Long id,@RequestBody UserReqDTO dto){
		try {
			 return ResponseEntity.ok(userService.updateUserDetails(id, dto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
	}
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUserDetailsSoft(id);
			return ResponseEntity.ok(
	                new ApiResponse("User deleted successfully", "SUCCESS"));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(),"Failed"));
		}
		
	 }
	
}
