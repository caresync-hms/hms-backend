package com.backend.dtos;

import com.backend.entity.BedStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BedRespDTO {
	  private Long id;
	    private String bedNumber;
	    private Long patientId;
	    private String status;
	    private String patientName;
	    private Long wardId;
}
