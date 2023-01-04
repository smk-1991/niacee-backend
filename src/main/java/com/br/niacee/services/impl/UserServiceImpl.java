package com.br.niacee.services.impl;


import com.br.niacee.entities.UserData;
import com.br.niacee.exception.ErroAutenticacao;
import com.br.niacee.exception.RegraNegocioException;
import com.br.niacee.repository.UserRepository;
import com.br.niacee.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository repository;
	private final PasswordEncoder encoder;


	@Override
	public UserData autenticar(String cpf, String senha) {
		Optional<UserData> usuario = repository.findByCpf(cpf);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado.");
		}
		
		boolean senhasBatem = encoder.matches(senha, usuario.get().getSenha());
		
		if(!senhasBatem) {
			throw new ErroAutenticacao("Senha inválida.");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public UserData salvarUsuario(UserData userData) {
		validarCpf(userData.getCpf());
		criptografarSenha(userData);
		return repository.save(userData);
	}

	private void criptografarSenha(UserData userData) {
		String senha = userData.getSenha();
		String senhaCripto = encoder.encode(senha);
		userData.setSenha(senhaCripto);
	}

	@Override
	public void validarCpf(String cpf) {
		boolean existe = repository.existsByCpf(cpf);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}

	}

}
