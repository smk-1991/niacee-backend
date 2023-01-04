package com.br.niacee.services;


import com.br.niacee.entities.UserData;

import java.util.Optional;

public interface UserService {

	UserData autenticar(String cpf, String senha);
	
	UserData salvarUsuario(UserData userData);
	
	void validarCpf(String cpf);
	
}