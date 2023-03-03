package com.arcesi.banque.security;

public class UIConstanteUrls {
	
	/***====================================POST========================================**/
	/**
	 * URL Creation d'un client 
	 */
	public static final  String  CREATE_CLIENT="/mabanque/v1/clients/api/**";
	
	
	/***====================================   GET ========================================**/
	/**
	 * URL connexion d'un utilisateur 
	 */
    public  static final String SING_IN="/login/**";
    
    /**
	 * URL confirmation token 
	 */
	public static final String CONFIRMATION_TOKEN="/mabanque/v1/users/api/confirm/**";
	
	/**
	 * URL get Client Connectee
	 */
	public static final String FIND_USER_CLIENT_CONNECTE="/mabanque/v1/clients/api/findUserConnecte";
}
