package com.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.custom_exceptions.ResourceNotFoundException;
import com.backend.dtos.ApiResponse;
import com.backend.dtos.UserRegDTO;
import com.backend.dtos.UserResp;
import com.backend.dtos.UserRespDTO;
import com.backend.entity.Gender;
import com.backend.entity.Role;
import com.backend.entity.Status;
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



	@Override
	public UserRespDTO registerUser(UserRegDTO dto) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setFirstname(dto.getFirstName());
		
		user.setLastname(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPassword("na");
		  user.setUsername(dto.getEmail()); 
		 user.setStatus(Status.ACTIVE); 
		user.setPhone(dto.getPhone());
		user.setRole(Role.ROLE_PATIENT);
		user.setDob(LocalDateTime.now());
		user.setGender(dto.getGender());
		userRepository.save(user);

		UserRespDTO resp = new UserRespDTO();
		resp.setId(user.getId());
		resp.setFirstname(user.getFirstname());
		resp.setRole(user.getRole());

		return resp;
	}
}
