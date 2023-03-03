package com.arcesi.banque.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.banque.request.UserRequest;
import com.arcesi.banque.response.UserResponse;

public interface UserRoleApi {

	@PostMapping(value ="/api/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);
	
	
	@GetMapping(value = "/api/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> confirmToken(@RequestParam ("token") String token);

	@GetMapping(path ="/api/refreshToken")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
