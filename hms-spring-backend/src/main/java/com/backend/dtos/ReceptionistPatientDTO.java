package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.BloodGroup;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
	public class ReceptionistPatientDTO {

	    @NotNull
	    //private Long userId;

	    // USER fields
	    private String firstname;
	    private String lastname;
	    private String email;
	    private String phone;
	    private String gender;
	    private LocalDate dob;
	    private String password;

	    // PATIENT fields
	    private BloodGroup bloodGroup;
	    private String medicalHistory;
	}


