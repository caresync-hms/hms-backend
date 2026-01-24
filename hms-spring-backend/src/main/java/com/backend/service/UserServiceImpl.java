package com.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.custom_exceptions.ResourceNotFoundException;
import com.backend.dtos.ApiResponse;
import com.backend.dtos.UserResp;
import com.backend.entity.User;
import com.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	// depcy	
	private final UserRepository userRepository;	
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<UserResp> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll()
				.stream()
				.map(user -> modelMapper.map(user, UserResp.class))
				.toList();
	}

	

	@Override
	public UserResp getUserDetails(Long userId) {
		User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!!!!!"));

		return modelMapper.map(user, UserResp.class);
	}

	
	
	
	@Override
	public ApiResponse encryptPassword() {
		//get all users
		List<User> users = userRepository.findAll();	
		//user - persistent
		users.forEach(user ->
		 user.setPassword(passwordEncoder.encode(user.getPassword())));
		return new ApiResponse("Password encrypted", "Success");
	}
}
