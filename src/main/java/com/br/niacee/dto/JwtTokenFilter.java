package com.br.niacee.dto;


import com.br.niacee.services.JwtService;
import com.br.niacee.services.impl.SecurityUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class JwtTokenFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private SecurityUserDetailsService userDetailsService;

	public JwtTokenFilter(
			JwtService jwtService,
			SecurityUserDetailsService userDetailsService
			) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");

		if(authorization != null) {
			
			String token = authorization;
			boolean isTokenValid = jwtService.isTokenValido(token);
			
			if(isTokenValid) {
				String login = jwtService.obterLoginUsuario(token);
				UserDetails usuarioAutenticado = userDetailsService.loadUserByUsername(login);
				
				UsernamePasswordAuthenticationToken user = 
						new UsernamePasswordAuthenticationToken(
								usuarioAutenticado, null, usuarioAutenticado.getAuthorities());
				
				user.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );
				
				SecurityContextHolder.getContext().setAuthentication(user);
				
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
