package com.arcesi.banque.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.arcesi.banque.exceptions.excep.CannotBeNullException;

public class ControleSyntaxe {

	private final Random RANDOM = new SecureRandom();
	private final String ALPHANUMIRIQUE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	// Regex Alphanumeric and Space
	private static final String ALPHANUMERIQUE_PATTERN = "^[a-zA-Z\\s]+$";

	private static final String NUMERIQUE_PATTERN = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$";

	private static final String DATE_NAISSANCE_PATTERN = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

	private static final String ALPHA_PATTERN = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";

	// prix hors taxe, prix ttc,
	private static final String BIG_DECIMAL_PATTERN = "(\\d+\\.\\d{1,2})";
	// tva
	private static final String BIG_DECIMAL_PATTERN_TVA = "^(20|[1-9]?[0-9])$";

	// private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

	private static final String CODE_POSTAL_PATTERN = "[0-9]{5}";

	private static final String DEPATEMENT_NAISSANCE = "[0-9]{3}";

	private static final String TELEPHONE_PORTABLE_PATTERN = "^(\\+33|0|0033)[0-9]{9}$";
	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpeg|jpg|png|gif|bmp))$)";

	/** "MOTIF_RAISON_SOCIALE" */
	public static final String MOTIF_RAISON_SOCIALE = "[A-Z 0-9a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.@,&+%]{1,80}";

	/**
	 * Motif_SIRET
	 */
	public static final String MOTIF_SIRET = "[0-9]{14}";
	
	/**
	 * MOTIF PASSWOR
	 */
	// digit + lowercase char + uppercase char + punctuation + symbol
    private static final String PASSWORD_PATTERN ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	/**
	 * Validation Chaine de caractère Alphanumérique
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isAlphanumerique(final String name) {
		if (StringUtils.isEmpty(name)) {
			throw new CannotBeNullException("Ne peut pas être null");
		}
		return Pattern.matches(ALPHANUMERIQUE_PATTERN, name);
	}

	public static boolean isDateNaissanceValide(final String date) {
		return Pattern.matches(DATE_NAISSANCE_PATTERN, date);
	}

	public static boolean isAlpha(final String name) {
		if (StringUtils.isEmpty(name)) {
			throw new CannotBeNullException("Ne doit pas être null");
		}
		return Pattern.matches(ALPHA_PATTERN, name);
	}

	public static boolean isBigDecimal(String str) {
		Pattern p = Pattern.compile(BIG_DECIMAL_PATTERN);
		Matcher m = p.matcher(str);

		return m.matches();

	}

	public static boolean isBigDecimalTVA(String str) {
		Pattern p = Pattern.compile(BIG_DECIMAL_PATTERN_TVA);
		Matcher m = p.matcher(str);

		return m.matches();

	}

	public static boolean isEmailValide(String str) {
		if (StringUtils.isEmpty(str)) {
			throw new CannotBeNullException("ne doit pas être null");
		}
		return Pattern.matches(EMAIL_PATTERN, str);

	}

	public static boolean isDepartementNaissanceValide(String str) {
		if (StringUtils.isEmpty(str)) {
			throw new CannotBeNullException("ne doit pas être null");
		}
		return Pattern.matches(DEPATEMENT_NAISSANCE, str);
	}

	public static boolean isZip(String str) {
		if (StringUtils.isEmpty(str)) {
			throw new CannotBeNullException("ne doit pas être null");
		}
		return Pattern.matches(CODE_POSTAL_PATTERN, str);

	}

	public static boolean isTelephoneValide(final String str) {
		if (StringUtils.isEmpty(str)) {
			throw new CannotBeNullException("ne doit pas être null");
		}
		return Pattern.matches(TELEPHONE_PORTABLE_PATTERN, str);
	}

	public String generateStringId(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHANUMIRIQUE.charAt(RANDOM.nextInt(ALPHANUMIRIQUE.length())));
		}

		return new String(returnValue);
	}

	public static Boolean isNombreValide(String numeroRue) {
		if (StringUtils.isEmpty(numeroRue)) {
			throw new CannotBeNullException("Ne doit pas être null");
		}
		return Pattern.matches(NUMERIQUE_PATTERN, numeroRue);
	}

	public static Boolean isImageValide(String photoClient) {
		if (StringUtils.isEmpty(photoClient)) {
			throw new CannotBeNullException("Ne doit pas être null");
		}
		return Pattern.matches(IMAGE_PATTERN, photoClient);
	}

	public static Boolean isRaisonSocialeValide(String raisonSocial) {
		if (StringUtils.isEmpty(raisonSocial)) {
			throw new CannotBeNullException("Ne doit pas être null");
		}
		return Pattern.matches(MOTIF_RAISON_SOCIALE, raisonSocial);
	}

	public static Boolean isNumeroSiretValide(String numeroSiret) {
		if(StringUtils.isEmpty(numeroSiret)) {
			throw new CannotBeNullException("Siret Ne doit pas être null");
		}
		return Pattern.matches(MOTIF_SIRET, numeroSiret);
	}

	public static Boolean isValidePassWord(String password) {
		if(StringUtils.isEmpty(password)) {
			throw new CannotBeNullException("password Ne doit pas être null");
		}
		return Pattern.matches(PASSWORD_PATTERN, password);
		
	}

	public static String mettrePremierLetteEnMajuscule(String str) {
		if(StringUtils.isEmpty(str)) {
			throw new CannotBeNullException("FirstName ne peut pas être null");
		}
		String premierLettre=str.substring(0, 1);
		String restLtrs = str.substring(1, str.length());
		StringBuilder st=new StringBuilder();
		st.append(premierLettre.toUpperCase());
		st.append(restLtrs.toLowerCase());
		return st.toString();
	}
}
