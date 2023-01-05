package com.br.niacee.services.impl;

import com.br.niacee.dto.TokenDTO;
import com.br.niacee.dto.UserDTO;
import com.br.niacee.entities.UserData;
import com.br.niacee.exception.AuthenticationError;
import com.br.niacee.exception.BusinessRuleException;
import com.br.niacee.repository.UserRepository;
import com.br.niacee.services.JwtService;
import com.br.niacee.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static com.br.niacee.helper.UserBuilder.userDataBuilder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;


    @Override
    public TokenDTO authenticate(UserDTO userDTO) {
        final String email = userDTO.getEmail();
        final String password = userDTO.getPassword();
        TokenDTO tokenDTO = null;

        UserData user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));

        if (Objects.isNull(user)) {
            throw new AuthenticationError("User not found!");
        }

        boolean passwordMatch = encoder.matches(password, user.getPassword());

        if (!passwordMatch) {
            throw new AuthenticationError("Invalid Password!");
        }

        return getTokenDTO(tokenDTO, user);
    }

    private TokenDTO getTokenDTO(TokenDTO tokenDTO, UserData user) {
        try {
            String token = jwtService.gerarToken(user);
            tokenDTO = new TokenDTO(user.getEmail(), token);
        } catch (AuthenticationError e) {
            e.getMessage();
        }
        return tokenDTO;
    }


    @Override
    @Transactional
    public void saveUser(UserDTO userDTO) {
        emailValidator(userDTO.getEmail());
        passwordCriptographer(userDTO);
        repository.save(userDataBuilder(userDTO));
    }

    private void passwordCriptographer(UserDTO userDTO) {
        String password = userDTO.getPassword();
        String encryptedPassword = encoder.encode(password);
        userDTO.setPassword(encryptedPassword);
    }

    @Override
    public void emailValidator(String email) {
        boolean exists = repository.existsByEmail(email);
        if (exists) {
            throw new BusinessRuleException("There already is an user in the system with this credential.");
        }

    }

}
