package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Gender;
import com.backend.entity.Role;
import com.backend.entity.Status;

import lombok.Data;

@Data
public class UserRespDTO {
	 private Long id;
	    private String username;
	    private String firstname;
	    private String lastname;
	    private Role role;
	    private Gender gender;
	    private LocalDate dob;
	    private String email;
	    private String phone;
	    private String address;
	    private Status status;
}
