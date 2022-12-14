package com.br.niacee.services;

import com.br.niacee.entities.UserData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;


public interface JwtService {

	String gerarToken(UserData userData);
	
	Claims getClaims(String token) throws ExpiredJwtException;
	
	boolean isTokenValido(String token);
	
	String getUserLogin(String token );
}
