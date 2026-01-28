package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;
import com.backend.entity.Status;

import lombok.Data;

@Data
public class UpdateUserDTO {

	private String firstname;
	private String lastname;
	private String phone;
	private String address;
	private Gender gender;
	private LocalDate dob;
	private Status status;
}
