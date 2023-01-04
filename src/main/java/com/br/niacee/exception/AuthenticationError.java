package com.br.niacee.exception;

public class AuthenticationError extends RuntimeException {

	public AuthenticationError(String mensagem) {
		super(mensagem);
	}
}
