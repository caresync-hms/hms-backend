package com.backend.dtos;

import com.backend.entity.BloodGroup;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
	public class ReceptionistPatientDTO {

	    @NotNull
	    private Long userId;

	    @NotNull
	    private BloodGroup bloodGroup;

	    private String medicalHistory;
	}


