package com.backend.dtos;

import com.backend.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private String doctorName;
    private String specialization;
    private String doctorDepartment;
    private String doctorPhoneNo;
    private String doctorEmail;
    private Gender gender;
}

