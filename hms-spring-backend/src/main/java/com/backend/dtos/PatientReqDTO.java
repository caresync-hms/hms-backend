package com.backend.dtos;

import java.time.LocalDateTime;

import com.backend.entity.BloodGroup;
import com.backend.entity.Gender;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientReqDTO {
	private Long userId;
    private BloodGroup bloodGroup;
    private Gender gender;
    private String medicalHistory;
    private LocalDateTime admitDate;
    private LocalDateTime dischargeDate;
}
