package com.example.dcim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.dcim.config.JwtUtil;
import com.example.dcim.dto.AuthRequest;
import com.example.dcim.dto.AuthResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final JwtUtil jwtUtil;

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		logger.info("Login attempt for user: {}", authRequest.getUsername());

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
			String token = jwtUtil.generateToken(userDetails.getUsername());

			logger.info("Login successful for user: {}", authRequest.getUsername());
			return ResponseEntity.ok(new AuthResponse(token));

		} catch (Exception e) {
			logger.error("Login failed for user: {}", authRequest.getUsername());
			return ResponseEntity.status(401).body("{\"error\": \"Invalid credentials\"}");
		}
	}

}
