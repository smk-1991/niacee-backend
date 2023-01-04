package com.br.niacee.services;
import com.br.niacee.dto.TokenDTO;
import com.br.niacee.dto.UserDTO;

public interface UserService {

	TokenDTO authenticate(UserDTO userDTO);
	
	void saveUser(UserDTO userDTO);
	
	void cpfValidator(String cpf);
	
}