package com.backend.service;

import java.util.List;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.UserRespDTO;

public interface UserService {
//method to fetch all users
	List<UserRespDTO> getAllUsers();

	UserRespDTO getUserDetails(Long userId);

	ApiResponse encryptPassword();
}
