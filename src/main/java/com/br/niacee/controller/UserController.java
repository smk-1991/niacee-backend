package com.br.niacee.controller;

import com.br.niacee.dto.TokenDTO;
import com.br.niacee.dto.UserDTO;
import com.br.niacee.services.JwtService;
import com.br.niacee.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String > authenticate(@RequestBody UserDTO dto) {
        TokenDTO tokenDTO = userService.authenticate(dto);
        return new ResponseEntity<>(tokenDTO.getToken(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserDTO dto) {
        userService.saveUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
