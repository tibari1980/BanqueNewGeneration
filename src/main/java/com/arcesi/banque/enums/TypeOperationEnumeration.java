package com.arcesi.banque.enums;

public enum TypeOperationEnumeration {

	DEBUT("DEBUT", "Dé"),

	CREDIT("CREDIT", "Crédit");

	/** identifiant de l'enum **/
	private final String id;
	/** libelle de l'énum **/
	private final String libelle;

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param libelle
	 */
	private TypeOperationEnumeration(String id, String libelle) {
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
