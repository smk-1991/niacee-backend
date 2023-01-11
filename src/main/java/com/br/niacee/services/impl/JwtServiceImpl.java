package com.br.niacee.services.impl;

import com.br.niacee.entities.UserData;
import com.br.niacee.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.key-signature}")
	private String keySignature;

	@Override
	public String gerarToken(UserData userData) {
		long exp = Long.parseLong(expiration);
		LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(exp);
		Instant instant = expirationDateTime.atZone( ZoneId.systemDefault() ).toInstant();
		Date data = Date.from(instant);
		
		String tokenExpiration = expirationDateTime.toLocalTime()
				.format(DateTimeFormatter.ofPattern("HH:mm"));

		return Jwts
							.builder()
							.setExpiration(data)
							.setSubject(userData.getEmail())
							.claim("userid", userData.getId())
							.claim("email", userData.getEmail())
							.claim("expirationTime", tokenExpiration)
							.signWith( SignatureAlgorithm.HS512 , keySignature)
							.compact();
	}

	@Override
	public Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(keySignature)
				.parseClaimsJws(token)
				.getBody();
	}

	@Override
	public boolean isTokenValido(String token) {
		try {
			Claims claims = getClaims(token);
			Date dataEx = claims.getExpiration();
			LocalDateTime expirationTime = dataEx.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDateTime();
			boolean expirationTimeAtualized = LocalDateTime.now().isAfter(expirationTime);
			return !expirationTimeAtualized;
		}catch(ExpiredJwtException e) {
			return false;
		}
	}

	@Override
	public String getUserLogin(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

}
