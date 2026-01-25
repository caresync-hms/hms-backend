package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;
import com.backend.entity.Role;

import lombok.Data;

@Data
public class UserReqDTO {
	private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Role role;      
    private Gender gender;
    private LocalDate dob;
    private String email;
    private String phone;
    private String address;
}
