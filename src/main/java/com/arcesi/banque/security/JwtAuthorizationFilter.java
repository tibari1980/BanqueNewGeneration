package com.arcesi.banque.security;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals(SecurityConstants.URL_REFRESH_TOKEN)) {
			filterChain.doFilter(request, response);
		} else {
			String authorizationToken = request.getHeader(SecurityConstants.HEADER_STRING);
			if (authorizationToken != null && authorizationToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				try {
					String jwt = authorizationToken.substring(SecurityConstants.TOKEN_PREFIX.length());
					Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET.getBytes());
					//JWTVerifier jwtVerifier = JWT.require(algorithm).build();

					//DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
					DecodedJWT decodedJWT = JWT.decode(jwt);
					String userName = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim(SecurityConstants.CLAIM_STRING).asArray(String.class);
					Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					for (String r : roles) {
						authorities.add(new SimpleGrantedAuthority(r));
					}
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userName, null, authorities);

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				} catch (Exception e) {

					response.setHeader("Erreur-Message", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				}
			} else {
				filterChain.doFilter(request, response);
			}

		}

	}

}
