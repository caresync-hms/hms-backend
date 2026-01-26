package com.backend.service;

import org.springframework.stereotype.Service;

import com.backend.dtos.DashboardStatsDTO;
import com.backend.entity.Role;
import com.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DashboardService {

	private final UserRepository userRepository;

	public DashboardStatsDTO getDashboardStats() {
		return new DashboardStatsDTO(userRepository.countByRole(Role.ROLE_DOCTOR),
				userRepository.countByRole(Role.ROLE_PATIENT), userRepository.countByRole(Role.ROLE_RECEPTIONIST),
				userRepository.countByRole(Role.ROLE_ADMIN));
	}
}
