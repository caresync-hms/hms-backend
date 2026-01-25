package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;
import com.backend.entity.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserReqDTO {

	@NotBlank
	private String password;
	@NotBlank
	private String firstname;
	private String lastname;
	private Role role;
	private Gender gender;
	@Past
	private LocalDate dob;
	@NotBlank
	private String email;
	@NotBlank
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian Phone no")
	private String phone;
	private String address;
}
