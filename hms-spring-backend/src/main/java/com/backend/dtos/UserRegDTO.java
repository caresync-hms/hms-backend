package com.backend.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.backend.entity.Gender;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegDTO {
	@NotBlank(message = "FirstName is required")
	@Length(min = 3, max = 20, message = "first name must min 3 chars and max 20 chars")
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	 private Gender gender;
	@NotBlank
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian Phone no")
	private String phone;
	@Past
	private LocalDate dob;
	@Min(500)
	private Integer regAmount;

}
