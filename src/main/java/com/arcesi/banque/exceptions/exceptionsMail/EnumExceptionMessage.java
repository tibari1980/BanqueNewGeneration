package com.arcesi.banque.exceptions.exceptionsMail;

public enum EnumExceptionMessage {

	EMAIL_NOT_VALID("Email not valide"),
	COMPTE_CREE_SUCCESS("Activation du compte !!!"),
	COMPTE_VALIDATED_SUCCESSFULLY("Bienvenue dans votre Banque NG TIBARI"),
	USER_FOUNT_IN_OUR_DATA_BASE("User exist dans la base de données."),
	TOKEN_VIDE("Le token est vide !!!"),
	EMAIL_ALREADY_CONFIRMED("Email already confirmed"),
	TOKEN_NOT_FOUND("Token not found!!!!"),
	TOKEN_EXPIRED_TIME("Token expired"),
	USER_ENABLED_NOT_FOUND("L'utilisateur a activé est introuvable dans la bdd!!")
	;
	
	
	public final String code;
	
	private EnumExceptionMessage(final String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}
}
