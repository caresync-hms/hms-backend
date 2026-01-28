package com.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final CorsConfigurationSource corsConfigurationSource;
	private final PasswordEncoder passwordEncoder;
	private final CustomJwtFilter customJwtFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());

		http.cors(cors -> cors.configurationSource(corsConfigurationSource));
		http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/users/signin", "/patient/register", "/doctors",
						"/users/pwd-encryption")
				.permitAll().requestMatchers(HttpMethod.OPTIONS).permitAll()
				.requestMatchers(HttpMethod.POST, "/appointments/book").hasRole("PATIENT")
				.requestMatchers("/appointments/doctors/{userId}/upcoming", "/doctors/{userId}")
				.hasAnyRole("DOCTOR", "ADMIN").requestMatchers(HttpMethod.GET, "/patients").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
				// Receptionist APIs
				.requestMatchers("/receptionist/**").hasAnyRole("RECEPTIONIST", "ADMIN").requestMatchers("/patients/**")
				.hasRole("RECEPTIONIST").anyRequest().authenticated())
				.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);

		// enable Basic Auth scheme
		// http.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
