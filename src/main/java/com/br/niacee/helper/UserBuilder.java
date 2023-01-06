package com.br.niacee.helper;

import com.br.niacee.dto.UserDTO;
import com.br.niacee.entities.UserData;

public class UserBuilder {

    public static UserData userDataBuilder(UserDTO dto) {
        return UserData.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

}
