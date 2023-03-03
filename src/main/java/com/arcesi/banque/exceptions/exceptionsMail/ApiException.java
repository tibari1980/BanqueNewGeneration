package com.arcesi.banque.exceptions.exceptionsMail;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApiException {

	private final String message;
	private final HttpStatus httpStatus;
	private final ZonedDateTime zonedDateTime;

	public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.zonedDateTime = zonedDateTime;
	}

	
}
