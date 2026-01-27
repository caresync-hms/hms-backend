package com.backend.dtos;

import lombok.Data;

@Data
public class PatientIdDTO {
	 private Long id;        
	    private Long userId;    
	    private String firstname;
	    private String lastname;
	    private String email;
}
