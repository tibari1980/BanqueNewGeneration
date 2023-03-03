package com.arcesi.banque.utils;

import java.security.SecureRandom;
import java.util.Random;

public class GenerateurPassword {

	private static final String CARACTER_SPECIAUX = "&~#|`-_)('/?,;:.";

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static SecureRandom random = new SecureRandom();

	public static String generPassWord() {
		StringBuilder st = new StringBuilder();
		
		st.append(generateRandomStringToUpperCase(1));
		st.append(generateRandomStringToLowerCase(7));
		st.append(genererInt(0, 9));
		st.append(generateRandomStringCaracrtereSpeciString(1));
		return st.toString();
	}

	/**
	 * Générer des minuscules
	 * 
	 * @param length
	 * @return
	 */
	public static String generateRandomStringToLowerCase(int length) {
		if (length < 1)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(CHAR_LOWER.length());
			char rndChar = CHAR_LOWER.charAt(rndCharAt);

			sb.append(rndChar);

		}

		return sb.toString();

	}

	/**
	 * Générateur majuscule
	 * 
	 * @param length
	 * @return
	 */

	public static String generateRandomStringToUpperCase(int length) {
		if (length < 1)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(CHAR_UPPER.length());
			char rndChar = CHAR_UPPER.charAt(rndCharAt);
			sb.append(rndChar);

		}

		return sb.toString();

	}

	public static String generateRandomStringCaracrtereSpeciString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(CARACTER_SPECIAUX.length());
			char rndChar = CARACTER_SPECIAUX.charAt(rndCharAt);
			sb.append(rndChar);

		}

		return sb.toString();

	}

	/**
	 * 
	 * @param borneInf nombre inférieur
	 * @param borneSup nombre supérieur
	 * @return
	 */
	static int genererInt(int borneInf, int borneSup) {
		Random random = new Random();
		int nb;
		nb = borneInf + random.nextInt(borneSup - borneInf);
		return nb;
	}
}
