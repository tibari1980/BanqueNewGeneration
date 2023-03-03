package com.arcesi.banque.enums;

public enum TypeClientEnumeration {

	PHYSIQUE("PHYSIQUE"),

	MORALE("MORALE"),
	
	SANS("SANS");

	private String id;

	private TypeClientEnumeration(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * returning a string containg the values of class members
	 * 
	 * @param includeClassName
	 * @return
	 */
	protected String toString(boolean includeClassName) {
		StringBuffer str = new StringBuffer();
		if (includeClassName) {
			str.append("Type du Client, ");
		}
		str.append("PHYSIQUE='" + TypeClientEnumeration.PHYSIQUE + "',");
		str.append("MORALE='" + TypeClientEnumeration.MORALE + "',");
		return (str.toString());
	}
}
