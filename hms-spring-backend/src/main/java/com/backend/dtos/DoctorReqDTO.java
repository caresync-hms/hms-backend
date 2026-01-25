package com.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.StandardException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReqDTO {
	
    private Long userId;
    private Long departmentId;
    private String specialization;
}
