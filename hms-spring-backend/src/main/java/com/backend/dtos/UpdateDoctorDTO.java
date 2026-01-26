package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;
import com.backend.entity.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDoctorDTO {

	/* -------- User fields -------- */
	private String firstname;
	private String lastname;
	private String phone;
	private Gender gender;
	private LocalDate dob;
	private Status status;

	/* -------- Doctor fields -------- */
	private String specialization;
	private String departmentName;
}
