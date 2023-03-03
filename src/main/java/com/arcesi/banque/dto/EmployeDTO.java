package com.arcesi.banque.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import com.arcesi.banque.entites.EmployeBean;
import com.arcesi.banque.response.EmployeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class EmployeDTO extends AbstractEntityDTO {

	 
	private static final long serialVersionUID = 1L;
	private Long code;
	private String codeEmployeUnique;
	private String nomEmploye;
	private String prenomEmploye;
	private String emailEmploye;
	private String telephoneEmploye;
	private String photoEmploye;
	private LocalDate dateNaissanceEmploye;
	private Integer ageEmploye;
	private String paysNaissance;
	private String villeNaissance;
	private String departementNaissance;
	private Collection<CompteDTO> compteDTOs;
	private Collection<AdresseDTO> adresseDTOs;
	private EmployeDTO employeDTOSup;
	private Collection<GroupDTO> groupDTOs;
	
	@Builder
	public EmployeDTO(Instant createdAt, Instant updatedAt, Long code, String codeEmployeUnique, String nomEmploye,
			String prenomEmploye, String emailEmploye, String telephoneEmploye, String photoEmploye,
			LocalDate dateNaissanceEmploye, Integer ageEmploye, String paysNaissance, String villeNaissance,
			String departementNaissance, Collection<CompteDTO> compteDTOs, Collection<AdresseDTO> adresseDTOs, EmployeDTO employeDTOSup, Collection<GroupDTO> groupDTOs) {
		super(createdAt, updatedAt);
		this.code = code;
		this.codeEmployeUnique = codeEmployeUnique;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.emailEmploye = emailEmploye;
		this.telephoneEmploye = telephoneEmploye;
		this.photoEmploye = photoEmploye;
		this.dateNaissanceEmploye = dateNaissanceEmploye;
		this.ageEmploye = ageEmploye;
		this.paysNaissance = paysNaissance;
		this.villeNaissance = villeNaissance;
		this.departementNaissance = departementNaissance;
		
		this.employeDTOSup = employeDTOSup;
	}
	
	/**
	 * Méthode permettant de mapper l'employeBean to EmployeDTO
	 * @param bean {@link EmployeBean}
	 * @return dto {@link EmployeDTO}
	 */
	public static EmployeDTO toEntity(EmployeBean bean) {
		if(null==bean) {
			return null;
		}
		return EmployeDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.code(bean.getCode())
				.codeEmployeUnique(bean.getCodeEmployeUnique())
				.nomEmploye(bean.getNomEmploye())
				.prenomEmploye(bean.getPrenomEmploye())
				.emailEmploye(bean.getEmailEmploye())
				.ageEmploye(bean.getAgeEmploye())
				.dateNaissanceEmploye(bean.getDateNaissanceEmploye())
				.departementNaissance(bean.getDepartementNaissance())
				.villeNaissance(bean.getVilleNaissance())
				.paysNaissance(bean.getPaysNaissance())
				.telephoneEmploye(bean.getTelephoneEmploye())
				.adresseDTOs(
						null
						)
				.compteDTOs(
						 null
						)
				 
				.groupDTOs(
						null
						)
				.employeDTOSup(null
						)
				.photoEmploye(bean.getPhotoEmploye())
				.build();
	}
	
	/**
	 * Méthode permettant de mapper l'employeDTO to EmployeBean
	 * @param bean {@link EmployeDTO}
	 * @return bean {@link EmployeBean}
	 */
	public static EmployeBean fromEntity(EmployeDTO bean) {
		if(null==bean) {
			return null;
		}
		return EmployeBean.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.code(bean.getCode())
				.codeEmployeUnique(bean.getCodeEmployeUnique())
				.nomEmploye(bean.getNomEmploye())
				.prenomEmploye(bean.getPrenomEmploye())
				.emailEmploye(bean.getEmailEmploye())
				.ageEmploye(bean.getAgeEmploye())
				.dateNaissanceEmploye(bean.getDateNaissanceEmploye())
				.departementNaissance(bean.getDepartementNaissance())
				.villeNaissance(bean.getVilleNaissance())
				.paysNaissance(bean.getPaysNaissance())
				.telephoneEmploye(bean.getTelephoneEmploye())
				.adresseBeans(
						  null
						)
				.compteBeans(
						 null
						)
			  
				.groupBeans(
						 null
						)
				.employeBeanSup(null )
				.photoEmploye(bean.getPhotoEmploye())
				.build();
	}

	public static EmployeResponse toEmployeResponse(EmployeDTO bean) {
		if(null==bean) {
			return null;
		}
		return EmployeResponse.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.code(bean.getCode())
				.codeEmployeUnique(bean.getCodeEmployeUnique())
				.nomEmploye(bean.getNomEmploye())
				.prenomEmploye(bean.getPrenomEmploye())
				.emailEmploye(bean.getEmailEmploye())
				.ageEmploye(bean.getAgeEmploye())
				.dateNaissanceEmploye(bean.getDateNaissanceEmploye())
				.departementNaissance(bean.getDepartementNaissance())
				.villeNaissance(bean.getVilleNaissance())
				.paysNaissance(bean.getPaysNaissance())
				.telephoneEmploye(bean.getTelephoneEmploye())
				.photoEmploye(bean.getPhotoEmploye())
				.build();
	}
	
}
