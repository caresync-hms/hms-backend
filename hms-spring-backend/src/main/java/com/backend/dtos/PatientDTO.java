package com.backend.dtos;

import com.backend.entity.BloodGroup;
import com.backend.entity.Gender;
import com.backend.entity.Patient;

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

    public PatientDTO(Patient patient) {
        this.id = patient.getPatientId();
        this.firstName = patient.getUser().getFirstname();
        this.lastName = patient.getUser().getLastname();
        this.gender = patient.getUser().getGender();
        this.bloodGroup = patient.getBloodGroup();
        this.medicalHistory = patient.getMedicalHistory();
    }
}
