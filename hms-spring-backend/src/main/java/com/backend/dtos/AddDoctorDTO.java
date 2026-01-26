package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;
import com.backend.entity.Status;

import lombok.Data;

@Data
public class AddDoctorDTO {

	/* -------- User Fields -------- */
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private Gender gender;
	private LocalDate dob;
	private String password;

	/* -------- Doctor Fields -------- */
	private String specialization;
	private String departmentName;

	/* -------- Status -------- */
	private Status status; // ACTIVE by default
}
