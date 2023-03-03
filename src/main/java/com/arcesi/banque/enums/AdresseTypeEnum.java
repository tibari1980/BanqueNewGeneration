package com.arcesi.banque.enums;

public enum AdresseTypeEnum {
	
	BILLING("BIL","Billing"),
	
	SHIPPING("SHI","Shipping");
	
	/** identifiant de l'enum **/
	private final String id;
	/** libelle de l'énum  **/
	private final String libelle;
	
	/**
	 * Constructeur 
	 * @param id
	 * @param libelle
	 */
	private AdresseTypeEnum(String id, String libelle) {
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
