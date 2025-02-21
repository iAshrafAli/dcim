package com.example.dcim.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


	// Dummy in-memory users for testing (Replace with DB integration)
	private final List<UserDetails> users = List.of(
			User.withUsername("admin").password(new BCryptPasswordEncoder().encode("password")).roles("USER").build(),
			User.withUsername("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER").build());

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.stream().filter(user -> user.getUsername().equals(username)).findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

}
