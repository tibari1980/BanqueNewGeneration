package com.arcesi.banque.security;

public class SecurityConstants {

	public static final long EXPIRATION_TIME=864000;//dix jours
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String SING_UP_URL="/users";
	public static final String TOKEN_SECRET="dfg893hdc475zwerop4tghg4dfeqaas?=-01jznm0-9"; 
	//public static final String TOKEN_SECRET="tibari";
	public static final String CLAIM_STRING ="roles";
	public static final Object URL_REFRESH_TOKEN ="/mabanque/v1/users/api/refreshToken";

}
