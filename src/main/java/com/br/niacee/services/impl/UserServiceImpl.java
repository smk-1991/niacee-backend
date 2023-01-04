package com.br.niacee.services.impl;

import com.br.niacee.dto.TokenDTO;
import com.br.niacee.dto.UserDTO;
import com.br.niacee.entities.UserData;
import com.br.niacee.exception.AuthenticationError;
import com.br.niacee.exception.RegraNegocioException;
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
        final String cpf = userDTO.getCpf();
        final String password = userDTO.getSenha();
        TokenDTO tokenDTO = null;

        UserData user = repository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("User not found!"));

        if (Objects.isNull(user)) {
            throw new AuthenticationError("User not found!");
        }

        boolean passwordMatch = encoder.matches(password, user.getSenha());

        if (!passwordMatch) {
            throw new AuthenticationError("Invalid Password.");
        }

        return getTokenDTO(tokenDTO, user);
    }

    private TokenDTO getTokenDTO(TokenDTO tokenDTO, UserData user) {
        try {
            String token = jwtService.gerarToken(user);
            tokenDTO = new TokenDTO(user.getNome(), token);
        } catch (AuthenticationError e) {
            e.getMessage();
        }
        return tokenDTO;
    }


    @Override
    @Transactional
    public void saveUser(UserDTO userDTO) {
        cpfValidator(userDTO.getCpf());
        passwordCriptographer(userDTO);
        repository.save(userDataBuilder(userDTO));
    }

    private void passwordCriptographer(UserDTO userDTO) {
        String password = userDTO.getSenha();
        String encryptedPassword = encoder.encode(password);
        userDTO.setSenha(encryptedPassword);
    }

    @Override
    public void cpfValidator(String cpf) {
        boolean exists = repository.existsByCpf(cpf);
        if (exists) {
            throw new RegraNegocioException("There already is an user with this credential.");
        }

    }

}
