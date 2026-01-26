package com.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatsDTO {
	private long doctors;
	private long patients;
	private long receptionists;
	private long admins;
}
