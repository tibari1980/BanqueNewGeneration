package com.arcesi.banque.controllers.imp;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.banque.controllers.UserRoleApi;
import com.arcesi.banque.dto.UserDTO;
import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.exceptions.exceptionsMail.ApiRequestException;
import com.arcesi.banque.exceptions.exceptionsMail.EnumExceptionMessage;
import com.arcesi.banque.request.UserRequest;
import com.arcesi.banque.response.UserResponse;
import com.arcesi.banque.security.SecurityConstants;
import com.arcesi.banque.services.AppUserService;
import com.arcesi.banque.services.IConfirmationTokenService;
import com.arcesi.banque.utils.Constants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@RequestMapping(value = Constants.APP_ROOT + "/users")
public class UserRestApi implements UserRoleApi {
	
	private final IConfirmationTokenService iConfirmationTokenService;
	private final AppUserService appUserService;

	@Override
	public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
		log.info("Inside méthode createUser in controllers UerRestApi {}",userRequest.getEmail());
		return null;
	}

	@Override
	public ResponseEntity<String> confirmToken(String token) {
		log.info("Inside méthode confirmToken in UserRestApi : {}",token);
		if(StringUtils.isEmpty(token)) {
			throw new ApiRequestException(EnumExceptionMessage.TOKEN_VIDE.getCode());
		}
		
		return new ResponseEntity<String>(iConfirmationTokenService.confirmToken(token), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String authorizationToken = request.getHeader(SecurityConstants.HEADER_STRING);
		if (authorizationToken != null && authorizationToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			try {
				String jwt = authorizationToken.substring(SecurityConstants.TOKEN_PREFIX.length());
				Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET.getBytes());
				DecodedJWT decodedJWT = JWT.decode(jwt);

				String userName = decodedJWT.getSubject();
				UserBean user = UserDTO.fromEntity(appUserService.loadUserByUserName(userName));
				String jwt_access_token = JWT.create().withSubject(user.getEmail())
						.withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim(SecurityConstants.CLAIM_STRING,
								user.getRoleBeans().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> tokens = new HashedMap();
				tokens.put("access-token", jwt_access_token);
				tokens.put("refresh-token", jwt);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception e) {

				throw e;
			}
		} else {
			throw new RuntimeException("Refresh token required!!!");
		}
		return null;

	}

}
