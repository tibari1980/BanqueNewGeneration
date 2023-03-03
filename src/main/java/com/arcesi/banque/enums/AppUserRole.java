package com.arcesi.banque.enums;

public enum AppUserRole {
	

	CLIENT("ROLE_CLIENT"),
	EMPLOYE("ROLE_EMPLOYE"),
	ADMINISTRATEUR("ROLE_ADMINISTRATEUR");
	
	private final String id;
	
	private AppUserRole(final String id) {
	this.id=id;
	}
	
	public String getId() {
		return id;
	}

}
