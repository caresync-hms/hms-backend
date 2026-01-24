package com.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dtos.UserReqDTO;
import com.backend.dtos.UserRespDTO;
import com.backend.entity.Status;
import com.backend.entity.User;
import com.backend.repository.UserRepository;

import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserRespDTO> getAllUsers() {
		return userRepository.findAll().stream().map(user->modelMapper.map(user, UserRespDTO.class)).toList();
	}

	@Override
	public UserRespDTO addUser(UserReqDTO userReqDTO) {
		User user=modelMapper.map(userReqDTO, User.class);
		User saved=userRepository.save(user);
		
		return modelMapper.map(saved,UserRespDTO.class);
	}

	@Override
	public UserRespDTO getUserDetails(Long userId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return modelMapper.map(user, UserRespDTO.class);
	}

	@Override
	public UserRespDTO updateUserDetails(Long userId, UserReqDTO userReqDTO) {
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		modelMapper.map(userReqDTO, user);
		User saved=userRepository.save(user);
		return modelMapper.map(saved, UserRespDTO.class);
	}


	@Override
	public UserRespDTO updateUserStatus(Long userId, Status status) {
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		user.setStatus(status);
		User updatedUser=userRepository.save(user);
		return modelMapper.map(updatedUser, UserRespDTO.class);
	}

	@Override
	public void deleteUserDetailsSoft(Long userId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		//userRepository.delete(user);
		user.setStatus(Status.INACTIVE);
		userRepository.save(user);
		
	}
	
}
