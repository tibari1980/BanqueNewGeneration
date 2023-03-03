package com.arcesi.banque.exceptions.excep;

/**
 * 
 * @author Zeroual tibari 
 * ingénieur de développement
 *
 */
/**
 * 
 * Exception levée lorsque qu'une valeur ne devrait pas être null
 * cette exception évite d'avoir des NullPointerException dans on ne connait pas la cause
 *
 */
public class CannotBeNullException extends SystemException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable qui est null
	 */
	private final String nullVariable ;
	
	/**
	 * @param nomDeLaVariable qui est null
	 * @param nomDeLaVariable
	 */
	public CannotBeNullException(final String nomDeLaVariable) {
		super("LaVariable'"+nomDeLaVariable+"'ne doit pas etre 'null'");
		nullVariable=nomDeLaVariable;
	}

	/**
	 * renvoie le nom de la variable qui est null
	 * @return
	 */
	public String getNullVariable() {
		return nullVariable;
	}

	
	
}
