package com.backend.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.entity.Gender;
import com.backend.entity.Role;
import com.backend.entity.Status;
import com.backend.entity.User;
import com.backend.repository.UserRepository;

@Configuration
public class AdminBootstrap {

	@Value("${app.admin.email}")
	private String adminEmail;

	@Value("${app.admin.password}")
	private String adminPassword;

	@Bean
	CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		return args -> {
			if (userRepository.existsByEmail(adminEmail)) {
				return;
			}

			User admin = User.builder().firstname("System").lastname("Admin").email(adminEmail)
					.password(passwordEncoder.encode(adminPassword)).role(Role.ROLE_ADMIN).gender(Gender.MALE)
					.dob(LocalDate.of(1990, 1, 1)).status(Status.ACTIVE).build();

			userRepository.save(admin);
			System.out.println("Default admin user created");
		};
	}
}
