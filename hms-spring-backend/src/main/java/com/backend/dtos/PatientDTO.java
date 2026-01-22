package com.backend.dtos;

import java.time.LocalDateTime;

import com.backend.entity.BloodGroup;
import com.backend.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private BloodGroup bloodGroup;
	private String medicalHistory;
}
