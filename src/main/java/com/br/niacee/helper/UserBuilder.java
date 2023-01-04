package com.br.niacee.helper;

import com.br.niacee.dto.UserDTO;
import com.br.niacee.entities.UserData;

public class UserBuilder {

    public static UserData userDataBuilder(UserDTO dto) {
        UserData userData = UserData.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .senha(dto.getSenha()).build();
        return userData;
    }

}
