package com.arcesi.banque.utils;

import java.util.regex.Pattern;

import com.arcesi.banque.exceptions.excep.CannotBeNullException;

public interface UtilsValidation {

	

	/** "MOTIF_CHAMPS_TIT" */
	public static final String MOTIF_CHAMPS_TIT = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,80}";
	

	/** permet de valider la forme du numéro du téléphone portable */
	public static final String MOTIFI_TELEPHONE_PORTABLE = "((\\+33)|0)(6|7)([-\\\\ /]{0,1}[\\\\-]{0,1}[0-9]{2}){4}";

	/**
	 * Pattern de supression des retour a la ligne<br>
	 * http://www.exampledepot.com/egs/java.util.regex/RemLine.html
	 */
	public static final Pattern EOL_REMOVE_PATTERN = Pattern.compile("([\\r\\n]+)");

	/**
	 * Pattern de remplacement de doublequote par des simples quote
	 */
	public static final Pattern QUOTE_REMOVE_PATTERN = Pattern.compile("(\")");

	/**
	 * Pattern de remplacement de ";"
	 */
	public static final Pattern COMMAS_REMOVE_PATTERN = Pattern.compile("[;]+");

	/** "MOTIF_EMAIL_CONTACT" */
	// public static final String MOTIF_EMAIL_CONTACT =
	// "[A-Za-z0-9!#$%&'*+-/=?^_`{|}\\.]+@[A-Za-z0-9\\-\\.]+\\.[A-Za-z0-9]{1,4}";

	/** "MOTIF_CHAMPS_TIT" */
	// public static final String MOTIF_CHAMPS_TIT = "[A-Z
	// a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,80}";

	/** "MOTIF_IDENTITE_TIT" */
	public static final String MOTIF_IDENTITE_TIT = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,161}";

	/** "MOTIF_RAISON_SOCIALE" */
	// public static final String MOTIF_RAISON_SOCIALE = "[A-Z
	// 0-9a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.@,&+%]{1,80}";

	/** "MOTIF_SIREN" */
	public static final String MOTIF_SIREN = "[0-9]{9}";

	/** "MOTIF_DEP_NAISSANCE" */
	public static final String MOTIF_DEP_NAISSANCE = "[0-9AB]{2,3}";

	/** "MOTIF_COMMUNE_NAISSANCE" */
	public static final String MOTIF_COMMUNE_NAISSANCE = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,80}";

	/** "MOTIF_PAYS_NAISSANCE" */
	public static final String MOTIF_PAYS_NAISSANCE = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,80}";

	/** "MOTIF_ETAGE_IMMEUBLE" */
	public static final String MOTIF_ETAGE_IMMEUBLE = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.,0-9]{1,38}";

	/** "MOTIF_NUM_VOIE" */
	public static final String MOTIF_NUM_VOIE = "[0-9]{1,4}";

	/** "MOTIF_QUELQUE_CHAMP_ADR" */
	public static final String MOTIF_QUELQUE_CHAMP_ADR = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.,0-9]{1,80}";

	/** "MOTIF_TYPE_VOIE" */
	public static final String MOTIF_TYPE_VOIE = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ\\-\\']{1,50}";

	/** "MOTIF_CODE_POSTAL" */
	public static final String MOTIF_CODE_POSTAL = "([0-9][0-7]|[1-8]8|[0-9][1-7]|[0-8]8|[0-9]9)[0-9][0-9][0-9]";

	/** "MOTIF_NUM_USAGE_MILITAIRE" */
	public static final String MOTIF_NUM_USAGE_MILITAIRE = "[A-Z 0-9]{1,12}";

	/** "MOTIF_NUM_EXPLOIT_USAGE_AGR" */
	public static final String MOTIF_NUM_EXPLOIT_USAGE_AGR = "[A-Za-z0-9]*";

	/** "MOTIF_PRIX_VEHICULE" */
	public static final String MOTIF_PRIX_VEHICULE = "^[1-9]\\d*$";

	/** "MOTIF_COMMUNE_DVS" */
	public static final String MOTIF_COMMUNE_DVS = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,80}";

	/**
	 * [JAA] MANTIS 12463 : Ajouter les caractères speciaux a accepter dans cette
	 * variable
	 */
	/** "MOTIF_INFO_MENTION" */
	public static final String MOTIF_INFO_MENTION = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«@&+%\\\\\"'\\(\\)\\-/\\.,0-9]{1,80}";

	/** "MOTIF_IDENTITE_EXPEDITION" */
	public static final String MOTIF_IDENTITE_EXPEDITION = "[A-Z0-9a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.@,&+%]{1}[A-Z 0-9a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.@,&+%]{0,160}";

	/** "MOTIF_CHAMPS_CONTACT : modifier les coordonnées d'un contact (MAP-PPA)" */
	public static final String MOTIF_CHAMPS_CONTACT = "[A-Z a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝŸŒàáâãäåæçèéêëìíîïñòóôõöùúûüýÿœ»«\\\\\"'\\(\\)\\-/\\.]{1,70}";

	/** "MOTIF_TEL_CONTACT" */
	public static final String MOTIF_TEL_CONTACT = "[0-9 ]{10,19}";

	/** "MOTIF_EMAIL_CONTACT" */
	public static final String MOTIF_EMAIL_CONTACT = "[A-Za-z0-9!#$%&'*+-/=?^_`{|}\\.]+@[A-Za-z0-9\\-\\.]+\\.[A-Za-z0-9]{1,4}";

	/** "MOTIF_NUM_SINISTRE_OP_VE" */
	public static final String MOTIF_NUM_SINISTRE_OP_VE = ".{1,20}";

	/** "MOTIF_ID_SUIVI_SUPERV_BATCH" */
	public static final String MOTIF_ID_SUIVI_SUPERV_BATCH = "[0-9]+|[A-Z]{3}[0-9]+";

	/** "MOTIF_CATEGORIES_O" */
	public static final String MOTIF_CATEGORIES_O = "[O]{1}[1-4]{1}";

	/** "MOTIF_CATEGORIES_R" */
	public static final String MOTIF_CATEGORIES_R = "[R]{1}[a | b]{1}[1-4]{1}";

	/** "MOTIF_CATEGORIES_S" */
	public static final String MOTIF_CATEGORIES_S = "[S]{1}[a | b]{1}[1-2]{1}";

	/** "MOTIF_NUM_FORMULE_CI" */
	public static final String MOTIF_NUM_FORMULE_CI = "[A-Za-z0-9]{1,11}";

	/// ===========================================================================

	/**
	 * Permet de valider l'adresse mail pour les coordonnées d'un contact.
	 * 
	 * @param chaine la valeur à tester
	 * @return la chaine valide
	 * @throws CannotBeNullException si le paramètre "chaine" est null
	 */
	public static boolean validerEmailCoordonneeContact(final String chaine) throws CannotBeNullException {
		if (null == chaine) {
			throw new CannotBeNullException("La chaine est vide");
		}
		return Pattern.matches(MOTIF_EMAIL_CONTACT, chaine);
	}

}
