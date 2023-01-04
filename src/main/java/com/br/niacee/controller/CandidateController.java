package com.br.niacee.controller;

import com.br.niacee.dto.TokenDTO;
import com.br.niacee.dto.UserDTO;
import com.br.niacee.entities.Candidate;
import com.br.niacee.entities.UserData;
import com.br.niacee.exception.ErroAutenticacao;
import com.br.niacee.exception.RegraNegocioException;
import com.br.niacee.services.CandidateService;
import com.br.niacee.services.JwtService;
import com.br.niacee.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/candidate")
@CrossOrigin
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final UserService userService;
    private final JwtService jwtService;


    @PostMapping(value = "/createCandidate")
    public ResponseEntity<Void> createCandidate(@RequestBody Candidate candidate){
        candidateService.addCandidate(candidate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllCandidates")
    public ResponseEntity<List<Candidate>> findAllCandidates(){
        List<Candidate> candidateList = candidateService.findAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate( @RequestBody UserDTO dto ) {
        try {
            UserData usuarioAutenticado = userService.autenticar(dto.getCpf(), dto.getSenha());
            String token = jwtService.gerarToken(usuarioAutenticado);
            TokenDTO tokenDTO = new TokenDTO( usuarioAutenticado.getNome(), token);
            return ResponseEntity.ok(tokenDTO);
        }catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> save( @RequestBody UserDTO dto ) {

        UserData userData = UserData.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .senha(dto.getSenha()).build();

        try {
            UserData usuarioSalvo = userService.salvarUsuario(userData);
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
