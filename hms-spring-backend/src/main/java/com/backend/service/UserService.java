package com.backend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.backend.dtos.UserReqDTO;
import com.backend.dtos.UserRespDTO;
import com.backend.entity.Status;
import com.backend.entity.User;

import io.swagger.v3.oas.models.responses.ApiResponse;

public interface UserService {
	List<UserRespDTO> getAllUsers();

    UserRespDTO addUser(UserReqDTO userReqDTO);

    UserRespDTO getUserDetails(Long userId);

    UserRespDTO updateUserDetails(Long userId, UserReqDTO userReqDTO);

    void deleteUserDetailsSoft(Long userId);
    
    UserRespDTO updateUserStatus(Long userId, Status status);
}
