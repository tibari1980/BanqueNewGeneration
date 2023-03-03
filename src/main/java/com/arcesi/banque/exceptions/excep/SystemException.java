package com.arcesi.banque.exceptions.excep;

public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SystemException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param message de l'exception
	 * @param cause de l'exception
	 */
	public SystemException(final String message,final Throwable cause) {
		super(message,cause);
	}
	
	/**
	 * 
	 * @param cause de l'exception
	 */
	public SystemException(final Throwable cause) {
		super(cause);
	}
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result=null;
		if(null!=getCause()) {
			result=super.toString()+":"+getCause().getLocalizedMessage();
		}else {
			result=super.toString();
		}
		return result;
	}

}
