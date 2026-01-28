package com.backend.service;

import java.util.List;

import com.backend.dtos.ApiResponse;
import com.backend.dtos.UpdateUserDTO;
import com.backend.dtos.UserReqDTO;
import com.backend.dtos.UserRespDTO;
import com.backend.entity.Role;
import com.backend.entity.Status;

public interface UserService {

	List<UserRespDTO> getAllUsers();

	UserRespDTO getUserDetails(Long userId);

	ApiResponse encryptPassword();

	UserRespDTO addUser(UserReqDTO dto);

	UserRespDTO updateUser(Long userId, UpdateUserDTO dto);

	void updateUserStatus(Long userId, Status status);

	List<UserRespDTO> getUsersByRole(Role role);
}
