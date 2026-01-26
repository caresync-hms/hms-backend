package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.BloodGroup;
import com.backend.entity.Gender;
import com.backend.entity.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientDTO {

	/* -------- User fields -------- */
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String password;
	private Gender gender;
	private LocalDate dob;
	private Status status;

	/* -------- Patient fields -------- */
	private BloodGroup bloodGroup;
	private String medicalHistory;
}
