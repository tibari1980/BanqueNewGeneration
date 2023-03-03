package com.arcesi.banque.enums;

public enum CompteStatusEnumeration {
	
BLOQUE("BQ","bloqued"),
	
	ACTIVATED("AC","activated"),
	
	CREATED("CR","created"),
	
	SUSPENDED("SP","suspended");
	
	/** identifiant de l'enum **/
	private final String id;
	/** libelle de l'énum  **/
	private final String libelle;
	
	/**
	 * Constructeur 
	 * @param id
	 * @param libelle
	 */
	private CompteStatusEnumeration(String id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public String getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}
	
	
	

}
