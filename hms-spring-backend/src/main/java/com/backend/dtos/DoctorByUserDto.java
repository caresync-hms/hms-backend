package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorByUserDto {

	private Long id ; 
    private String departmentName;
    private String specialization;
    private String doctorName;
    private String phone;
    private String email;
    private LocalDate dob;
    private Gender gender;
    private String address;
}

