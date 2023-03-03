package com.arcesi.banque.enums;

public enum MessageErrors {

	//Categorie Messages
	CATEGORY_DESIGNATION("Ce champs ne peut pas être vide  !!!"),
	CATEGORY_DESIGNATION_SIZE_MIN("La designation doit contenir au moins 4 caractères !"),
	CATEGORY_DESIGNATION_SIZE_MAX("La designation ne doit pas dépasser 40 caractères !"),
	CATEGORY_DESIGNATION_ALPHA("La designation doit être alphnumérique ex:(portable samsung) ."),
	
	//Article Messages
	ARTICLE_NOT_VALID("Veuillez remplir tous les champs."),
	ARTICLE_DESIGNATION("Ce chmaps ne peut pas être vide"),
	ARTICLE_DESIGNATION_SIZE_MIN("La designation doit contenir au moins 4 caractères !"),
	ARTICLE_DESIGNATION_SIZE_MAX("La designation ne doit pas dépasser 40 caractères !"),
	ARTICLE_DESIGNATION_ALPHA("La designation doit être alphnumérique ex:(portable samsung) ."), 
	ARTICLE_PRIXHT_NULL("Le prix de l'article ne peut pas être null !"), 
	ARTICLE_PRIX_LESS_ZERO("Le prix ne doit pas être inférieur à zéro !"), 
	ARTICLE_PRIX_BIG_DECIMAL("Veuillez Saisir un prix valide format décimal 100.24 "),
	CATEGORY_NULL("La Category ne peut pas être null"), 
	CATEGORY_IDENTIFIANT("L'identifiant ne peut pas être null"), 
	ARTICLE_TAUX_TVA_BIG_DECIMAL("Veuillez saisir un taux valide ex: (14.2)."),
	ARTICLE_TVA_LESS_ZERO("Le taux tva ne peut pas être < à 0 . ex: 5 ou 14 ou 20"),
	ARTICLE_PRIX_DEPASSE_1500("Le prix ne doit pas dépasser 1500.00 !!"),
	ARTICLE_TVA_NOT_VALIDE("Le Taux de tva ne doit pas dépasser 20 % et doit être supérieur à 0  ex :{5.00,7.00,14.00,20.00} ."), 
	ARTICLE_PRIXHT_DEPASSE_1500("Le prix de l'article ne doit pas dépasser 1500 euros"),
	ARTICLE_TTVA_NULL("Taux de tva ne peut pas être null"),
	//Client 
	CLIENT_NOT_VALID("Veuillez remplir tous les champs!"),
	CLIENT_NOM("Nom du client ne peut pas être vide !"),
	CLIENT_NOM_SIZE_MIN("Le nom doit contenir au moins 4 caractères ."), 
	CLIENT_NOM_SIZE_MAX("Le nom ne doit pas dépasser 40 caractères ."), 
	CLIENT_NOM_ALPHA("Le nom doit être alphanumérique (tibari zeroual !"), 
	CLIENT_PRENOM("Le prénom du client ne doit pas être vide"),
	CLIENT_RAISON_SOCIAL_VIDE("la raison sociale ne peut pas être vide"),
	CLIENT_NUMERO_SIRET_VIDE("Le numéro de siret ne peut pas être vide"),
	CLIENT_VILLE("La ville  du client ne doit pas être vide"),
	CLIENT_DATE_NAISSANCE_VIDE("La date de naissance du client ne doit pas être vide"),
	CLIENT_PAYS_NAISSANCE("Le pays de naissance du client ne doit pas être vide"),
	CLIENT_PRENOM_SIZE_MIN("Le prenom doit contenir au moins 4 caractères ."), 
	CLIENT_RAISON_SOCIALE_SIZE_MIN("La raison sociale doit contenir au moins 4 caractères."),
	CLIENT_NUMERO_SIRET_SIZE("Le numéro de siret doit contenir 14 chiffres."),
	CLIENT_VILLE_SIZE_MIN("la ville de naissance doit contenir au moins 4 caractères ."), 
	CLIENT_PAYS_NAISSANCE_SIZE_MIN("le pays de naissance doit contenir au moins 4 caractères ."), 
	CLIENT_PRENOM_SIZE_MAX("Le prénom ne doit pas dépasser 40 caractères."),
	CLIENT_RAISON_SOCIAL_MAX("La raison sociale ne doit pas dépasséer 40 caractères."),
	CLIENT_VILLE_SIZE_MAX("La ville de naissance ne doit pas dépasser 40 caractères."),
	CLIENT_PAYS_NAISSANCE_SIZE_MAX("La ville de naissance ne doit pas dépasser 40 caractères."),
	CLIENT_PRENOM_ALPHA("Le prénom doit être alphabétique."),
	CLIENT_RAISON_SOCIALE_PATTERN("La raison sociale n'est pas valide le fromat."),
	CLIENT_NUMERO_SIRET_NON_VALID("Le numéro de siret n'est pas valide doit contenir 14 chiffre."),
	CLIENT_VILLE_ALPHA("La ville de naissance doit être  alphabétique ex : Paris."),
	CLIENT_DATE_NAISSANCE_NOT_VALID("La date de naissance n'est pas valide  ex : 1980-12-02."),
	CLIENT_PAYS_NAISSANCE_ALPHA("Le pays de naissance doit être  alphabétique ex : FRANCE."),
	CLIENT_EMAIL("Email ne peut pas être vide"),
	CLIENT_EMAIL_SIZE_MIN("Email doit contenir au moins 15 caractères ."),
	CLIENT_EMAIL_SIZE_MAX("Email ne doit pas dépassser  100 caractères ."), 
	CLIENT_EMAIL_ALPHA("Adresse email non valid!"),
	CLIENT_TELEPHONE("Telephone ne peut pas être vide"),
	CLIENT_PHOTO("La photo ne peut pas être vide;"),
	CLIENT_DEPARTEMENT_NAISSANCE_SIZE("Le département de naissance doit contenir 3 chiffre ex: 075 !"),
	CLIENT_TELEPHONE_SIZE_MIN("Le téléphone doit contenir 11 chiffres"),
	CLIENT_TELEPHONE_ALPHA("Le format du téléphone n'est pas valide ex :(0033625487145) ."),
	CLIENT_PHOTO_EXTENSION("L'extension de la photo n'est pas valide (jpeg (jpg|png|gif|bmp) "),
	CLIENT_TELEPHONE_SIZE_MAX("Le téléphone doit contenir 11 chiffres merci."),
	
	
	ADRESSE_POSTALE("L'adresse postale ne peut pas être null"),
	ADRESSE_NOT_VALIDE("Veuillez saisir une adresse postale valide !!!"), 
	ADRESSE_POSTALE_PAYS_VIDE("Le pays ne peut pas être vide ex : FRANCE !!!!"), 
	ADRESSE_POSTALE_TYPE_VIDE("Le type de l'adresse ne peut pas être vide !!!!"),
	ADRESSE_POSTALE_PAYS_DIFFERENT_FRANCE("on accepte pas un pays différent de la france merci"), 
	ADRESSE_POSTALE_PAYS_SIZE_NOT_VALIDE("Le champs pays doit contenir au moins 6 caractères "),
	ADRESSE_POSTALE_FIRST_ADRESSE_VIDE("Le Champs first adresse ne peut pas être vide !!!"),
	ADRESSE_POSTALE_FIRST_ADRESSE_SIZE_NOT_VALIDE("Ce champs doit contenir au moins 10 caractères et ne doit pas dépasser 100 caractères!!!! "),
	ADRESSE_POSTALE_SECONDE_ADRESSE_VIDE("Le Champs seconde adresse ne peut pas être vide !!!"),
	ADRESSE_POSTALE_SECONDE_ADRESSE_SIZE_NOT_VALIDE("Ce champs doit contenir au moins 10 caractères et ne doit pas dépasser 100 caractères!!!!"),
	ADRESSE_POSTALE_VILLE_VIDE("Ce champs ville ne peut pas être null !!!"),
	ADRESSE_POSTALE_VILLE_ADRESSE_SIZE_NOT_VALIDE("Ce champs la ville doit contenir au moins 4 caractères et ne doit pas dépasser 40 caractères !!!!"),
	ADRESSE_POSTALE_CODE_POSTALE_VIDE("Ce champs code postale ne peut pas être null !!! "),
	ADRESSE_POSTALE_CODE_POSTALE_ADRESSE_NOT_VALIDE("Le champs code postale  ne doit pas dépasser 5 chiffre!!!!"),
	ADRESSE_CODE_POSTALE_NEST_PAS_VALIDE("Le champs  code postale n'est pas valide ex: 75014 !!!!"), 
	ADRESSE_POSTALE_NBRS("Le client doit avoir deux adresses postale!!"),
	
	FOURNISSEUR_NOT_VALID("Veuillez remplir tous les champs !!!!!"),
	CLIENT_DEPARTEMENT_NAISSANCE("Le département de naissance ne doit pas être null"),
	
	CLIENT_DEPARTEMENT_NAISSANCE_NOT_VALID("le format de départment de naissance n'est pas valide ex: 075"),
	CLIENT_NOT_VALIDE("Le client n'est pas valide"), 
	ADRESSE_POSTALE_NOM_RUE_VIDE("Le nom de la rue ne peut pas être vide "),
	ADRESSE_NOM_RUE_SIZE_MIN("Le nom de la rue  doit contenir au moins 4 caractères!"), 
	ADRESSE_NOM_RUE_SIZE_MAX("Le nom de la rue ne doit pas dépasser 60 caractères !"),
	ADRESSE_NOM_RUE_ALPHA("Le nom de la rue doit être alphabétique!"),
	ADRESSE_NUM_RUE_VIDE("Le numéro de la rue ne peut pas être vide"),
	ADRESSE_NUMERO_RUE_NUMERIQUE("Le numéro de la rue doit être numérique !"), 
	
	
	
	USER_NOT_VALID("L'utilisateur n'est pas valide"),
	
	
	;
	
	
	
	private String code;
	
	private MessageErrors(final String p_code) {
		this.code=p_code;
	}
	
	public String getCode() {
		return code;
	}
}
