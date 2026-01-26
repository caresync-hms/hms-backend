package com.backend.service;

import java.util.List;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.UserRegDTO;
import com.backend.dtos.UserResp;
import com.backend.dtos.UserRespDTO;

public interface UserService {
//method to fetch all users
	List<UserResp> getAllUsers();
	UserResp getUserDetails(Long userId);
	
	ApiResponse encryptPassword();
	UserRespDTO registerUser(UserRegDTO dto);
}
