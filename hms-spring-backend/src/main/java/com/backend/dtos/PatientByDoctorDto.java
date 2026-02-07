package com.backend.dtos;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientByDoctorDto {

    private String patientName;      // user.firstname + user.lastname
    private Gender gender;            // user.gender
    private LocalDate dob;            // user.dob
    private String phone;             // user.phone
    private String address;           // user.address
    private String medicalHistory;    // patient.medicalHistory
    private LocalDateTime admitDate;  // patient.admitDate
    private LocalDateTime dischargeDate; // patient.dischargeDate
}
