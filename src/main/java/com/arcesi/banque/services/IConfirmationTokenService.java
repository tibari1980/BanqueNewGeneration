package com.arcesi.banque.services;

import com.arcesi.banque.entites.ConfirmationToken;

public interface IConfirmationTokenService {
	
	ConfirmationToken saveConfirmationToken(ConfirmationToken token);
	
	ConfirmationToken findConfirmationTokenByToken( final String token);

	String confirmToken(String token);

	 

	 

}
