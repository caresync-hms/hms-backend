package com.backend.service;

import org.springframework.stereotype.Service;

import com.backend.dtos.DashboardStatsDTO;
import com.backend.entity.Role;
import com.backend.entity.Status;
import com.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DashboardService {

	private final UserRepository userRepository;

	public DashboardStatsDTO getDashboardStats() {
		return new DashboardStatsDTO(userRepository.countByRoleAndStatus(Role.ROLE_DOCTOR, Status.ACTIVE),
				userRepository.countByRoleAndStatus(Role.ROLE_PATIENT, Status.ACTIVE),
				userRepository.countByRoleAndStatus(Role.ROLE_RECEPTIONIST, Status.ACTIVE),
				userRepository.countByRoleAndStatus(Role.ROLE_ADMIN, Status.ACTIVE));
	}
}
