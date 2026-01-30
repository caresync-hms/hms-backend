package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Role;
import com.backend.entity.Status;
import com.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String em);

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String email);

	long countByRoleAndStatus(Role role, Status status);

	List<User> findByRole(Role role);

	boolean existsByRole(Role roleAdmin);

}