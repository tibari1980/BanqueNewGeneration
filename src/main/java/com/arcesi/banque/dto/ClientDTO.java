package com.arcesi.banque.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.arcesi.banque.entites.AdresseBean;
import com.arcesi.banque.entites.ClientBean;
import com.arcesi.banque.entites.CompteBean;
import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.enums.MessageErrors;
import com.arcesi.banque.enums.SexEnumeration;
import com.arcesi.banque.enums.TypeClientEnumeration;
import com.arcesi.banque.exceptions.ErrorsCodes;
import com.arcesi.banque.exceptions.InvalidEntityException;
import com.arcesi.banque.exceptions.excep.CannotBeNullException;
import com.arcesi.banque.request.AdresseRequest;
import com.arcesi.banque.request.ClientRequest;
import com.arcesi.banque.response.ClientResponse;
import com.arcesi.banque.response.CompteResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class ClientDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;
	private Long code;
	private String codeClientUnique;
	private TypeClientEnumeration typeClient;
	private SexEnumeration sex;
	private String nomClient;
	private String prenomClient;
	private String emailClient;
	private String telephoneClient;
	private String photoClient;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissanceClient;
	private Integer ageClient;
	private String paysNaissance;
	private String villeNaissance;
	private String departementNaissance;
	private String raisonSocial;
	private String numeroSiret;
	private Collection<CompteDTO> compteDTOs;
	private List<AdresseDTO> adresseDTOs;

	@Builder
	public ClientDTO(Instant createdAt, Instant updatedAt, Long code, String codeClientUnique,
			TypeClientEnumeration typeClient, SexEnumeration sex, String nomClient, String prenomClient,
			String emailClient, String telephoneClient, String photoClient, LocalDate dateNaissanceClient,
			Integer ageClient, String paysNaissance, String villeNaissance, String departementNaissance,
			String raisonSocial, String numeroSiret, Collection<CompteDTO> compteDTOs,
			List<AdresseDTO> adresseDTOs) {
		super(createdAt, updatedAt);
		this.code = code;
		this.codeClientUnique = codeClientUnique;
		this.typeClient = typeClient;
		this.sex = sex;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.telephoneClient = telephoneClient;
		this.photoClient = photoClient;
		this.dateNaissanceClient = dateNaissanceClient;
		this.ageClient = ageClient;
		this.paysNaissance = paysNaissance;
		this.villeNaissance = villeNaissance;
		this.departementNaissance = departementNaissance;
		this.raisonSocial = raisonSocial;
		this.numeroSiret = numeroSiret;
		this.compteDTOs = compteDTOs;
	}

	/**
	 * Méthode permettant de mapper un objet ClienBean to ClientDTO
	 * @param bean {@link ClientBean }
	 * @return bean {@link ClientDTO }
	 */
	 
	public static ClientDTO toEntity(ClientBean bean) {
		if(null==bean) {
			throw new InvalidEntityException(MessageErrors.CLIENT_NOT_VALIDE.getCode());
		}
		ClientDTO dto  =  ClientDTO.builder()
				.code(bean.getCode())
				.codeClientUnique(bean.getCodeClientUnique())
				.updatedAt(bean.getUpdatedAt())
				.createdAt(bean.getCreatedAt())
				.nomClient(bean.getNomClient())
				.prenomClient(bean.getPrenomClient())
				.dateNaissanceClient(bean.getDateNaissanceClient())
				.departementNaissance(bean.getDepartementNaissance())
				.ageClient(bean.getAgeClient())
				.emailClient(bean.getEmailClient())
				.numeroSiret(bean.getNumeroSiret())
				.paysNaissance(bean.getPaysNaissance())
				.villeNaissance(bean.getVilleNaissance())
				.photoClient(bean.getPhotoClient())
				.raisonSocial(bean.getRaisonSocial())
				.sex(bean.getSex())
				.telephoneClient(bean.getTelephoneClient())
				.typeClient(bean.getTypeClient())
				 
				.build();
		//Recupération des adresse pour le client
		if (CollectionUtils.isNotEmpty(bean.getAdresseBeans())) {
			List<AdresseDTO> adresseDTOs = new ArrayList<AdresseDTO>();
			for (AdresseBean b : bean.getAdresseBeans()) {
				adresseDTOs.add(AdresseDTO.toEntity(b));
			}
			if (CollectionUtils.isNotEmpty(adresseDTOs)) {
				dto.setAdresseDTOs(adresseDTOs);
			}

		}
		if (CollectionUtils.isNotEmpty(bean.getCompteBeans())) {
			List<CompteDTO> comptesDtos = new ArrayList<CompteDTO>();
			for (CompteBean c : bean.getCompteBeans()) {
				if (c instanceof CompteCourantBean) {
					comptesDtos.add(CompteCourantDTO.toEntity((CompteCourantBean) c));
				} else if (c instanceof CompteEpargneBean) {
					comptesDtos.add(CompteEpargneDTO.toEntity((CompteEpargneBean) c));
				}
			}
			if (CollectionUtils.isNotEmpty(comptesDtos)) {
				dto.setCompteDTOs(comptesDtos);
			}
		}
		
		return dto;
	}
	
	/**
	 * Méthode permettant de mapper un objet ClientDTO to ClientBean
	 * @param dto {@link ClientDTO }
	 * @return dto {@link ClientBean }
	 */
	public static ClientBean fromEntity(ClientDTO dto) {
		if(null==dto) {
			throw new InvalidEntityException("L'objet client ne peut pas être null");
		}
		ClientBean bean= ClientBean.builder()
				.code(dto.getCode())
				.codeClientUnique(dto.getCodeClientUnique())
				.updatedAt(dto.getUpdatedAt())
				.createdAt(dto.getCreatedAt())
				.nomClient(StringUtils.isBlank(dto.getNomClient())? dto.getNomClient():dto.getNomClient().trim().toLowerCase())
				.prenomClient(StringUtils.isBlank(dto.getPrenomClient())? dto.getPrenomClient(): dto.getPrenomClient().trim().toUpperCase())
				.dateNaissanceClient(dto.getDateNaissanceClient())
				.departementNaissance(dto.getDepartementNaissance())
				.ageClient(Period.between(dto.getDateNaissanceClient(),LocalDate.now()).getYears())
				.emailClient(StringUtils.isNotBlank(dto.getEmailClient())? dto.getEmailClient().trim():dto.getEmailClient())
				.numeroSiret(StringUtils.isNotBlank(dto.getNumeroSiret())? dto.getNumeroSiret().trim():dto.getNumeroSiret())
				.paysNaissance(StringUtils.isNotBlank(dto.getPaysNaissance())?dto.getPaysNaissance().trim().toUpperCase():dto.getPaysNaissance())
				.photoClient(StringUtils.isNotBlank(dto.getPhotoClient())?dto.getPhotoClient().trim():dto.getPhotoClient())
				.villeNaissance(dto.getVilleNaissance())
				.raisonSocial(
						StringUtils.isBlank(dto.getRaisonSocial())?
								dto.getRaisonSocial():dto.getRaisonSocial().trim().toUpperCase()
						)
				.sex(dto.getSex())
				.telephoneClient(dto.getTelephoneClient())
				.typeClient(dto.getTypeClient())
				.build();
		//getSex client Homme femme **/
		getSexClient(dto, bean);
		/** type client physique ou morale **/
		getTypeClient(dto, bean);
		//récupération adressse du client
		bean.setAdresseBeans(CollectionUtils.isNotEmpty(dto.getAdresseDTOs())?getListAdresseFromArrayList(dto.getAdresseDTOs()):null);
		return bean;
	}

	private static void getTypeClient(ClientDTO dto, ClientBean bean) {
		if(TypeClientEnumeration.PHYSIQUE.equals(dto.getTypeClient())) {
			bean.setTypeClient(TypeClientEnumeration.PHYSIQUE);
		}else if(TypeClientEnumeration.MORALE.equals(dto.getTypeClient())) {
			bean.setTypeClient(TypeClientEnumeration.MORALE);
		}
	}

	private static void getSexClient(ClientDTO dto, ClientBean bean) {
		if(SexEnumeration.HOMME.equals(dto.getSex())) {
			bean.setSex(SexEnumeration.HOMME);
		}else if(SexEnumeration.FEMME.equals(dto.getSex())) {
			bean.setSex(SexEnumeration.FEMME);
		}
	}
	
	
	public static ClientResponse toClientResponse(ClientDTO dto) {

		if(null==dto) {
			throw new CannotBeNullException("L'objet client ne peut pas être null");
		}
		ClientResponse bean= ClientResponse.builder()
				.code(dto.getCode())
				.codeClientUnique(dto.getCodeClientUnique())
				.updatedAt(dto.getUpdatedAt())
				.createdAt(dto.getCreatedAt())
				.nomClient(StringUtils.isBlank(dto.getNomClient())? dto.getNomClient():dto.getNomClient().trim().toUpperCase())
				.prenomClient(StringUtils.isBlank(dto.getPrenomClient())? dto.getPrenomClient(): dto.getPrenomClient().trim().toUpperCase())
				.dateNaissanceClient(dto.getDateNaissanceClient())
				.departementNaissance(StringUtils.isNotBlank(dto.getDepartementNaissance())?dto.getDepartementNaissance().trim():dto.getDepartementNaissance())
				.ageClient(Period.between(dto.getDateNaissanceClient(),LocalDate.now()).getYears())
				.emailClient(StringUtils.isNotBlank(dto.getEmailClient())? dto.getEmailClient().trim():dto.getEmailClient())
				.numeroSiret(StringUtils.isNotBlank(dto.getNumeroSiret())?dto.getNumeroSiret().trim():dto.getNumeroSiret())
				.paysNaissance(StringUtils.isNotBlank(dto.getPaysNaissance())?dto.getPaysNaissance().trim().toUpperCase():dto.getPaysNaissance())
				.photoClient(dto.getPhotoClient())
				.villeNaissance(StringUtils.isNotBlank(dto.getVilleNaissance())? dto.getVilleNaissance().toUpperCase().trim(): dto.getVilleNaissance())
				.raisonSocial(
						StringUtils.isBlank(dto.getRaisonSocial())?
								dto.getRaisonSocial():dto.getRaisonSocial().trim().toUpperCase()
						)
				.sex(dto.getSex())
				.telephoneClient(dto.getTelephoneClient())
				.typeClient(dto.getTypeClient())
				 .adresseResponses(
						dto.getAdresseDTOs()!=null?
								dto.getAdresseDTOs().stream().map(AdresseDTO::toAdresseResponse).collect(Collectors.toList()):null
						)
				.build();
		
		
		getAllCompteClient(dto, bean);
		return bean;
	
	}

	/**
	 * méthode permettant de transferer les comptes bean vers dto
	 * @param dto
	 * @param bean
	 */
	private static void getAllCompteClient(ClientDTO dto, ClientResponse bean) {
		if (CollectionUtils.isNotEmpty(dto.getCompteDTOs())) {
 			List<CompteResponse> comptesResponses = new ArrayList<CompteResponse>();
 			for (CompteDTO c : dto.getCompteDTOs()) {
 				if (c instanceof CompteCourantDTO) {
 					comptesResponses.add(CompteCourantDTO.toResponseCompte((CompteCourantDTO) c));
 				} else if (c instanceof CompteEpargneDTO) {
 					comptesResponses.add(CompteEpargneDTO.ResponseCompte((CompteEpargneDTO)c));
 				}
 			}
 			if (CollectionUtils.isNotEmpty(comptesResponses)) {
 				bean.setCompteResponses(comptesResponses);
 			}
 		}
	}

	public static ClientDTO clientRequestToClientDto(ClientRequest bean) {
		if(null==bean) {
			 throw new InvalidEntityException("Client invalide !!!");
		}
		ClientDTO dto  =  ClientDTO.builder()
				.nomClient(StringUtils.isNotEmpty(bean.getNomClient())? bean.getNomClient().trim().toUpperCase():null)
				.prenomClient(StringUtils.isNotEmpty(bean.getPrenomClient())? bean.getPrenomClient().trim().toUpperCase():null)	
				.departementNaissance(StringUtils.isNotEmpty(bean.getDateNaissanceClient())? bean.getDepartementNaissance():null)
				.emailClient(StringUtils.isNotEmpty(bean.getEmailClient())? bean.getEmailClient():null)
				.numeroSiret(StringUtils.isNotEmpty(bean.getNumeroSiret())? bean.getNumeroSiret():null)
				.paysNaissance(StringUtils.isNotEmpty(bean.getPaysNaissance())? bean.getPaysNaissance():null)
				.photoClient(StringUtils.isNotEmpty(bean.getPhotoClient())?bean.getPhotoClient():null)
				.villeNaissance(StringUtils.isNotEmpty(bean.getVilleNaissance())?bean.getVilleNaissance():null)
				.raisonSocial(StringUtils.isNotEmpty(bean.getRaisonSocial())?bean.getRaisonSocial():null)
				.telephoneClient(StringUtils.isNotEmpty(bean.getTelephoneClient())?bean.getTelephoneClient():null)
			     .build();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// convert String to LocalDate
		try {
			LocalDate localDateNaissance = LocalDate.parse(bean.getDateNaissanceClient(), formatter);
			dto.setDateNaissanceClient(localDateNaissance != null ? localDateNaissance : null);
		} catch (Exception e) {
			throw new InvalidEntityException(MessageErrors.CLIENT_DATE_NAISSANCE_NOT_VALID.getCode(),ErrorsCodes.CLIENT_DATE_NAISSANCE_NOT_VALID);
		}
		  
		if(StringUtils.isNotBlank(bean.getSex()) && bean.getSex().equalsIgnoreCase("HOMME")) {
			dto.setSex(SexEnumeration.HOMME);
		}else {
			dto.setSex(SexEnumeration.FEMME);
		}
		if(StringUtils.isNotBlank(bean.getTypeClient()) && bean.getTypeClient().equalsIgnoreCase("PHYSIQUE")) {
			dto.setTypeClient(TypeClientEnumeration.PHYSIQUE);
			dto.setRaisonSocial(null);
			dto.setNumeroSiret(null);
		}else if(StringUtils.isNotEmpty(bean.getTypeClient()) && bean.getTypeClient().equalsIgnoreCase("MORALE")) {
			dto.setTypeClient(TypeClientEnumeration.MORALE);
		}else {
			dto.setTypeClient(TypeClientEnumeration.SANS);
		}
		
		//Recupération des adresse pour le client
		if (CollectionUtils.isNotEmpty(bean.getAdresseRequests())) {
			List<AdresseDTO> adresseDTOs = new ArrayList<AdresseDTO>();
			for (AdresseRequest b : bean.getAdresseRequests()) {
				adresseDTOs.add(AdresseDTO.adresseRequestToAdresssDTO(b));
			}
			if (CollectionUtils.isNotEmpty(adresseDTOs)) {
				dto.setAdresseDTOs(adresseDTOs);
			}

		}
		 
		return dto;
	}
	
	private static List<AdresseBean> getListAdresseFromArrayList(List<AdresseDTO> adresseDTOs2) {
		List<AdresseBean> adresseBeans = new ArrayList<AdresseBean>();
		if (CollectionUtils.isNotEmpty(adresseDTOs2)) {
			for (AdresseDTO d : adresseDTOs2) {
				adresseBeans.add(AdresseDTO.fromEntity(d));
			}
		}
		return adresseBeans;
	}
}
