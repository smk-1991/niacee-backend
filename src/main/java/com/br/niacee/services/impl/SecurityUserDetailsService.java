package com.br.niacee.services.impl;

import com.br.niacee.entities.UserData;
import com.br.niacee.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;

	public SecurityUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		UserData usuarioEncontrado = userRepository
				.findByCpf(cpf)
				.orElseThrow(() -> new UsernameNotFoundException("CPF não cadastrado."));
		
		return User.builder()
				.username(usuarioEncontrado.getCpf())
				.password(usuarioEncontrado.getSenha())
				.roles("USER")
				.build();
	}

}
