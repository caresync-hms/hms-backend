package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.custom_exceptions.InvalidInputException;
import com.backend.dtos.AuthRequest;
import com.backend.dtos.AuthResp;
import com.backend.dtos.UserResp;
import com.backend.entity.User;
import com.backend.security.JWTUtils;
import com.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JWTUtils jwtUtils;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		System.out.println("in get all users");
		List<UserResp> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT) // SC 204
					.build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{userId}")

	@Operation(description = "Get user details by id ")
	public ResponseEntity<?> getUserDetailsById(@PathVariable @Min(1) @Max(100) Long userId) {
		System.out.println("in get user dtls " + userId);

		return ResponseEntity.ok(userService.getUserDetails(userId));

	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthRequest dto) {

		Authentication fullyAuthenticated = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

		User userEntity = (User) fullyAuthenticated.getPrincipal();

		// normalize roles for comparison
		String requestedRole = dto.getUserRole().toUpperCase();
		String actualRole = userEntity.getRole().name();

		if (!requestedRole.equals(actualRole)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new InvalidInputException("Role mismatch"));
		}

		return ResponseEntity.ok(
				new AuthResp("Login Successful", jwtUtils.generateToken(userEntity), actualRole, userEntity.getId()));
	}

	@PatchMapping("/pwd-encryption")
	@Operation(description = "Encrypt Password of all users")
	public ResponseEntity<?> encryptUserPassword() {
		log.info("encrypting users password ");
		return ResponseEntity.ok(userService.encryptPassword());

	}

}
