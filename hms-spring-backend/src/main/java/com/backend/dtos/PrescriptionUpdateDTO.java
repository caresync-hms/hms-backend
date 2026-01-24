package com.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class PrescriptionUpdateDTO {
		private String notes;         
	    private LocalDateTime issueDate;
}
