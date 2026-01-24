package com.backend.dtos;


import java.time.LocalDate;

import com.backend.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResp {
	private Long id;
	private String firstName;	
	private String lastName;	
	private String email;
	private LocalDate dob;
	private Role userRole;
	private String phone;
		
}
