package com.br.niacee.services.impl;

import com.br.niacee.entities.UserData;
import com.br.niacee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserData userData = userRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not signed up."));

		return User.builder()
				.username(userData.getFullName())
				.password(userData.getPassword())
				.roles("USER")
				.build();
	}

}
