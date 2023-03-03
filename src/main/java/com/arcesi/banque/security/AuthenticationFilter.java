package com.arcesi.banque.security;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arcesi.banque.request.utilRequestUser.LoginRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	     System.out.println("Méthode AttempAuthentication ");
		try {
			LoginRequest userLoginRequeste=new ObjectMapper().readValue(request.getInputStream(),LoginRequest.class);
			UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userLoginRequeste.getEmail(),userLoginRequeste.getPassword());
			return authenticationManager.authenticate(authenticationToken);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		 
		User user=(User) authResult.getPrincipal();
		
		Algorithm algorithm=Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET.getBytes());
		String jwt_access_token=JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
				.withIssuer(request.getRequestURL().toString()) 
				.withClaim(SecurityConstants.CLAIM_STRING, user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
	            .sign(algorithm);
		
		String jwt_refresh_token=JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
				//
				.withIssuer(request.getRequestURL().toString()) 
	            .sign(algorithm);
		Map<String, String> tokens=new HashMap<String, String>();
		tokens.put("access-token", jwt_access_token);
		tokens.put("refresh-token", jwt_refresh_token);
		response.setContentType("application/json");
		//response.setHeader(SecurityConstants.HEADER_STRING, jwt_access_token);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
	
}
