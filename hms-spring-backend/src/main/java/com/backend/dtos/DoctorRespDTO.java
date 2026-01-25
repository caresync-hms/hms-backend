package com.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRespDTO {
	 	private Long doctorId;
	    private String specialization;
	    private String departmentName;

	    private Long userId;
	    private String username;
}
