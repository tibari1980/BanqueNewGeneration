package com.arcesi.banque.exceptions;

public enum MessageErrorsConstants {
	
	USER_ALREADY_EXIST("Client existe dans notre base utilisateur vous devez choisir une autre adresse email!!"),
	CLIENT_INVALID("Le client est invalide");
	
	private String code;
	
	private MessageErrorsConstants(final String code) {
		this.code=code;
	}

	public String getCode() {
		return code;
	}
}
