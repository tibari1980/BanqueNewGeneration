package com.arcesi.banque.enums;

public enum TypeRoleEnum {
	ADMIN("ADMINISTRATEUR"),
	CLIENT("CLIENT"),
	MANAGER("MANAGER") ;
	
	private String id;
	
	private TypeRoleEnum(final String p_id) {
		this.id=p_id;
	}
	
	public String getId() {
		return id;
	}

}

