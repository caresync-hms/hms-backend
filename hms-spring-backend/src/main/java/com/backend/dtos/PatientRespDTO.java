package com.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.entity.BloodGroup;
import com.backend.entity.Gender;
import com.backend.entity.Patient;
import com.backend.entity.Status;
import com.backend.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRespDTO {
	private Long patientId;
    private Long userId;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDateTime dob;
    private Status status;

    private BloodGroup bloodGroup;
    private String medicalHistory;
    private LocalDateTime admitDate;
    private LocalDateTime dischargeDate;
    
    public PatientRespDTO(Patient patient) {
        this.patientId = patient.getId();

        User user = patient.getUser();
        this.userId = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.dob = user.getDob();
        this.status = user.getStatus();

        this.bloodGroup = patient.getBloodGroup();
        this.medicalHistory = patient.getMedicalHistory();
        this.admitDate = patient.getAdmitDate();
        this.dischargeDate = patient.getDischargeDate();
    }

	
}
