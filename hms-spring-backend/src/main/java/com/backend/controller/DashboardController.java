package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.DashboardStatsDTO;
import com.backend.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;

	@GetMapping("/stats")
	public ResponseEntity<DashboardStatsDTO> getStats() {
		return ResponseEntity.ok(dashboardService.getDashboardStats());
	}
}
