package com.arcesi.banque.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.arcesi.banque.dto.AdresseDTO;
import com.arcesi.banque.dto.ClientDTO;
import com.arcesi.banque.enums.MessageErrors;
import com.arcesi.banque.enums.TypeClientEnumeration;
import com.arcesi.banque.utils.ControleSyntaxe;
import com.arcesi.banque.utils.Utils;

public class ClientValidators {

	public static List<String> validate(ClientDTO clientDTO) {
		List<String> errors = new ArrayList<String>();

		if (clientDTO == null) {
			errors.add(MessageErrors.CLIENT_NOT_VALID.getCode());
		}

		if (clientDTO != null) {
			// Vérification Nom :
			if (StringUtils.isEmpty(clientDTO.getNomClient())) {
				errors.add(MessageErrors.CLIENT_NOM.getCode());
			} else if (StringUtils.isNotBlank(clientDTO.getNomClient())) {
				if (clientDTO.getNomClient().length() < 5) {
					errors.add(MessageErrors.CLIENT_NOM_SIZE_MIN.getCode());
				} else if (clientDTO.getNomClient().length() >= 40) {
					errors.add(MessageErrors.CLIENT_NOM_SIZE_MAX.getCode());
				} else if (Utils.isBooleanFalse(ControleSyntaxe.isAlphanumerique(clientDTO.getNomClient()))) {
					System.out.println("heolll");
				}
			}

			// Vérification Prenom
			if (StringUtils.isEmpty(clientDTO.getPrenomClient())) {
				errors.add(MessageErrors.CLIENT_PRENOM.getCode());
			} else if (clientDTO.getPrenomClient() != null) {
				if (clientDTO.getPrenomClient().length() < 4) {
					errors.add(MessageErrors.CLIENT_PRENOM_SIZE_MIN.getCode());
				} else if (clientDTO.getPrenomClient().length() >= 40) {
					errors.add(MessageErrors.CLIENT_PRENOM_SIZE_MAX.getCode());
				} else if (Utils.isBooleanFalse(ControleSyntaxe.isAlphanumerique(clientDTO.getPrenomClient()))) {
					errors.add(MessageErrors.CLIENT_PRENOM_ALPHA.getCode());
				}
			}

			// Vérification Email
			if (StringUtils.isEmpty(clientDTO.getEmailClient())) {
				errors.add(MessageErrors.CLIENT_EMAIL.getCode());
			} else if (StringUtils.isNotBlank(clientDTO.getEmailClient())) {
				if (clientDTO.getEmailClient().length() < 15) {
					errors.add(MessageErrors.CLIENT_EMAIL_SIZE_MIN.getCode());
				} else if (clientDTO.getEmailClient().length() >= 100) {
					errors.add(MessageErrors.CLIENT_EMAIL_SIZE_MAX.getCode());
				} else if (Utils.isBooleanFalse(ControleSyntaxe.isEmailValide(clientDTO.getEmailClient()))) {
					errors.add(MessageErrors.CLIENT_EMAIL_ALPHA.getCode());
				}
			}

			// Vérification telephone
			if (StringUtils.isEmpty(clientDTO.getTelephoneClient())) {
				errors.add(MessageErrors.CLIENT_TELEPHONE.getCode());
			} else if (clientDTO.getTelephoneClient().trim().length() > 0) {
				if (Utils.isBooleanFalse(ControleSyntaxe.isTelephoneValide(clientDTO.getTelephoneClient()))) {
					errors.add(MessageErrors.CLIENT_TELEPHONE_ALPHA.getCode());
				}
			}
			// Vérification nomPhoto
			if (StringUtils.isEmpty(clientDTO.getPhotoClient())) {
				errors.add(MessageErrors.CLIENT_PHOTO.getCode());
			} else if (StringUtils.isNotEmpty(clientDTO.getPhotoClient())) {
				if (Utils.isBooleanFalse(ControleSyntaxe.isImageValide(clientDTO.getPhotoClient()))) {
					errors.add(MessageErrors.CLIENT_PHOTO_EXTENSION.getCode());
				}
			}

			// Département de naissance
			if (StringUtils.isEmpty(clientDTO.getDepartementNaissance())) {
				errors.add(MessageErrors.CLIENT_DEPARTEMENT_NAISSANCE.getCode());
			} else if (clientDTO.getDepartementNaissance().trim().length() != 3) {
				errors.add(MessageErrors.CLIENT_DEPARTEMENT_NAISSANCE_SIZE.getCode());

			} else if (Utils.isBooleanFalse(
					ControleSyntaxe.isDepartementNaissanceValide(clientDTO.getDepartementNaissance()))) {
				errors.add(MessageErrors.CLIENT_DEPARTEMENT_NAISSANCE_NOT_VALID.getCode());
			}

			// Vérification ville de naissance
			if (StringUtils.isEmpty(clientDTO.getVilleNaissance())) {
				errors.add(MessageErrors.CLIENT_VILLE.getCode());
			} else if (StringUtils.isNotEmpty(clientDTO.getVilleNaissance())) {
				if (clientDTO.getVilleNaissance().length() < 4) {
					errors.add(MessageErrors.CLIENT_VILLE_SIZE_MIN.getCode());
				} else if (clientDTO.getVilleNaissance().length() >= 40) {
					errors.add(MessageErrors.CLIENT_VILLE_SIZE_MAX.getCode());
				} else if (Utils.isBooleanFalse(ControleSyntaxe.isAlpha(clientDTO.getVilleNaissance()))) {
					errors.add(MessageErrors.CLIENT_VILLE_ALPHA.getCode());
				}
			}

			// Vérification de la date de naissance client

			if (clientDTO.getDateNaissanceClient() == null) {
				errors.add(MessageErrors.CLIENT_DATE_NAISSANCE_VIDE.getCode());
			} else if (clientDTO.getDateNaissanceClient() != null) {
				LocalDate dateNaissance = clientDTO.getDateNaissanceClient();
				String formattedDateNaissance = dateNaissance.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				if (Utils.isBooleanFalse(ControleSyntaxe.isDateNaissanceValide(formattedDateNaissance))) {
					errors.add(MessageErrors.CLIENT_DATE_NAISSANCE_NOT_VALID.getCode());
				}
			}

			// Vérification pays de naissance
			if (StringUtils.isEmpty(clientDTO.getPaysNaissance())) {
				errors.add(MessageErrors.CLIENT_PAYS_NAISSANCE.getCode());
			} else if (StringUtils.isNotEmpty(clientDTO.getPaysNaissance())) {
				if (clientDTO.getPaysNaissance().length() < 4) {
					errors.add(MessageErrors.CLIENT_PAYS_NAISSANCE_SIZE_MIN.getCode());
				} else if (clientDTO.getPaysNaissance().length() >= 40) {
					errors.add(MessageErrors.CLIENT_PAYS_NAISSANCE_SIZE_MAX.getCode());
				} else if (Utils.isBooleanFalse(ControleSyntaxe.isAlpha(clientDTO.getPaysNaissance()))) {
					errors.add(MessageErrors.CLIENT_PAYS_NAISSANCE_ALPHA.getCode());
				}
			}
			// Vérification Prenom
			if (clientDTO.getTypeClient() != null && clientDTO.getTypeClient().equals(TypeClientEnumeration.MORALE)) {
				if (StringUtils.isEmpty(clientDTO.getRaisonSocial())) {
					errors.add(MessageErrors.CLIENT_RAISON_SOCIAL_VIDE.getCode());
				} else if (StringUtils.isNotEmpty(clientDTO.getRaisonSocial())) {
					if (clientDTO.getRaisonSocial().length() < 4) {
						errors.add(MessageErrors.CLIENT_RAISON_SOCIALE_SIZE_MIN.getCode());
					} else if (clientDTO.getRaisonSocial().length() >= 40) {
						errors.add(MessageErrors.CLIENT_RAISON_SOCIAL_MAX.getCode());
					} else if (Utils
							.isBooleanFalse(ControleSyntaxe.isRaisonSocialeValide(clientDTO.getRaisonSocial()))) {
						errors.add(MessageErrors.CLIENT_RAISON_SOCIALE_PATTERN.getCode());
					}
				}
				// Vérification le numéro de SIRET
				if (StringUtils.isEmpty(clientDTO.getNumeroSiret())) {
					errors.add(MessageErrors.CLIENT_RAISON_SOCIAL_VIDE.getCode());
				} else if (StringUtils.isNotEmpty(clientDTO.getNumeroSiret())) {
					if (clientDTO.getNumeroSiret().length() != 14) {
						errors.add(MessageErrors.CLIENT_NUMERO_SIRET_SIZE.getCode());
					} else if (Utils.isBooleanFalse(ControleSyntaxe.isNumeroSiretValide(clientDTO.getNumeroSiret()))) {
						errors.add(MessageErrors.CLIENT_NUMERO_SIRET_NON_VALID.getCode());
					}
				}
			}

			// Vérification des adresses postales
			if (CollectionUtils.isEmpty(clientDTO.getAdresseDTOs())) {
				errors.add(MessageErrors.ADRESSE_POSTALE.getCode());
			} else {
				if (CollectionUtils.isNotEmpty(clientDTO.getAdresseDTOs()) && clientDTO.getAdresseDTOs().size() != 2) {
					errors.add(MessageErrors.ADRESSE_POSTALE_NBRS.getCode());

				} else if (clientDTO.getAdresseDTOs().size() == 2) {
					AdresseDTO adresse1 = clientDTO.getAdresseDTOs().get(0);
					AdresseDTO adresse2 = clientDTO.getAdresseDTOs().get(1);
					/** si l'adresse est différent de null **/
					if (adresse1 != null) {

						if (StringUtils.isBlank(adresse1.getPays()) || adresse1.getPays().trim().length() == 0) {
							errors.add(MessageErrors.ADRESSE_POSTALE_PAYS_VIDE.getCode());
						}
						if (adresse1.getPays() != null && !adresse1.getPays().trim().equalsIgnoreCase("FRANCE")) {
							errors.add(MessageErrors.ADRESSE_POSTALE_PAYS_DIFFERENT_FRANCE.getCode());

						}

						if (StringUtils.isBlank(adresse1.getNomRue()) || adresse1.getNomRue().trim().length() == 0) {
							errors.add(MessageErrors.ADRESSE_POSTALE_NOM_RUE_VIDE.getCode());
						} else if (StringUtils.isNotBlank(adresse1.getNomRue())) {

							if (adresse1.getNomRue().length() < 4) {
								errors.add(MessageErrors.ADRESSE_NOM_RUE_SIZE_MIN.getCode());
							} else if (adresse1.getNomRue().length() >= 60) {
								errors.add(MessageErrors.ADRESSE_NOM_RUE_SIZE_MAX.getCode());
							} else if (Utils.isBooleanFalse(ControleSyntaxe.isAlpha(adresse1.getNomRue()))) {
								errors.add(MessageErrors.ADRESSE_NOM_RUE_ALPHA.getCode());
							}

						}

						if (StringUtils.isBlank(adresse1.getNumeroRue())
								|| adresse1.getNumeroRue().trim().length() == 0) {
							errors.add(MessageErrors.ADRESSE_NUM_RUE_VIDE.getCode());
						} else if (StringUtils.isNotBlank(adresse1.getNumeroRue())) {

							if (Utils.isBooleanFalse(ControleSyntaxe.isNombreValide(adresse1.getNumeroRue()))) {
								errors.add(MessageErrors.ADRESSE_NUMERO_RUE_NUMERIQUE.getCode());
							}

						}

						if (null == adresse1.getTypeAdresse()) {
							errors.add(MessageErrors.ADRESSE_POSTALE_TYPE_VIDE.getCode());

						}

						if (null == adresse1.getVille() || adresse1.getVille().trim().length() == 0) {
							errors.add(MessageErrors.ADRESSE_POSTALE_VILLE_VIDE.getCode());
						}

						if (StringUtils.isNotEmpty(adresse1.getVille())) {
							if (adresse1.getVille().length() > 40 || adresse1.getVille().length() < 4) {
								errors.add(MessageErrors.ADRESSE_POSTALE_VILLE_ADRESSE_SIZE_NOT_VALIDE.getCode());
							}
						}

						if (StringUtils.isEmpty(adresse1.getCodePostale())) {
							errors.add(MessageErrors.ADRESSE_POSTALE_CODE_POSTALE_VIDE.getCode());
						}
						if (StringUtils.isNotEmpty(adresse1.getCodePostale())) {

							if (Utils.isBooleanFalse(ControleSyntaxe.isZip(adresse1.getCodePostale().trim()))) {
								errors.add(MessageErrors.ADRESSE_CODE_POSTALE_NEST_PAS_VALIDE.getCode());
							}
						}
					}

					// Adresse Deux

					if (adresse2 != null) {

						if (StringUtils.isEmpty(adresse2.getPays())) {
							errors.add(MessageErrors.ADRESSE_POSTALE_PAYS_VIDE.getCode());
						}
						if (StringUtils.isNotEmpty(adresse2.getPays())
								&& !adresse2.getPays().trim().equalsIgnoreCase("FRANCE")) {
							errors.add(MessageErrors.ADRESSE_POSTALE_PAYS_DIFFERENT_FRANCE.getCode());

						}
						if (null == adresse2.getTypeAdresse()) {
							errors.add(MessageErrors.ADRESSE_POSTALE_TYPE_VIDE.getCode());

						}

						if (StringUtils.isEmpty(adresse2.getVille())) {
							errors.add(MessageErrors.ADRESSE_POSTALE_VILLE_VIDE.getCode());
						}

						if (adresse2.getVille() != null) {
							if (adresse2.getVille().length() > 40 || adresse2.getVille().length() < 4) {
								errors.add(MessageErrors.ADRESSE_POSTALE_VILLE_ADRESSE_SIZE_NOT_VALIDE.getCode());
							}
						}

						if (null == adresse2.getCodePostale() || adresse2.getCodePostale().length() == 0) {
							errors.add(MessageErrors.ADRESSE_POSTALE_CODE_POSTALE_VIDE.getCode());
						}
						if (adresse2.getCodePostale() != null) {

							if (Utils.isBooleanFalse(ControleSyntaxe.isZip(adresse2.getCodePostale().trim()))) {
								errors.add(MessageErrors.ADRESSE_CODE_POSTALE_NEST_PAS_VALIDE.getCode());
							}
						}

						if (StringUtils.isBlank(adresse2.getNomRue()) || adresse2.getNomRue().trim().length() == 0) {
							errors.add(MessageErrors.ADRESSE_POSTALE_NOM_RUE_VIDE.getCode());
						} else if (StringUtils.isNotEmpty(adresse2.getNomRue())) {

							if (adresse2.getNomRue().length() < 4) {
								errors.add(MessageErrors.ADRESSE_NOM_RUE_SIZE_MIN.getCode());
							} else if (adresse2.getNomRue().length() >= 60) {
								errors.add(MessageErrors.ADRESSE_NOM_RUE_SIZE_MAX.getCode());
							} else if (Utils.isBooleanFalse(ControleSyntaxe.isAlpha(adresse2.getNomRue()))) {
								errors.add(MessageErrors.ADRESSE_NOM_RUE_ALPHA.getCode());
							}

						}

						if (StringUtils.isBlank(adresse2.getNumeroRue())
								|| adresse2.getNumeroRue().trim().length() == 0) {
							errors.add(MessageErrors.ADRESSE_NUM_RUE_VIDE.getCode());
						} else if (StringUtils.isNotBlank(adresse2.getNumeroRue())) {

							if (Utils.isBooleanFalse(ControleSyntaxe.isNombreValide(adresse2.getNumeroRue()))) {
								errors.add(MessageErrors.ADRESSE_NUMERO_RUE_NUMERIQUE.getCode());
							}

						}
					}
				}
			}

		}

		return errors;
	}

}
