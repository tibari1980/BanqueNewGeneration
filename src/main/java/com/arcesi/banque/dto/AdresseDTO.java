package com.arcesi.banque.dto;

import java.time.Instant;

import org.apache.commons.lang.StringUtils;

import com.arcesi.banque.entites.AdresseBean;
import com.arcesi.banque.enums.AdresseTypeEnum;
import com.arcesi.banque.exceptions.InvalidEntityException;
import com.arcesi.banque.exceptions.excep.CannotBeNullException;
import com.arcesi.banque.request.AdresseRequest;
import com.arcesi.banque.response.AdresseResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdresseDTO extends AbstractEntityDTO {

	
	public AdresseDTO(Instant createdAt, Instant updatedAt) {
		super(createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;

	private Long codeAdresse;
	private String codeUniqueAdresse;
	private String numeroRue;
	private String nomRue;
	private String codePostale;
	private String ville;
	private String pays;
	private AdresseTypeEnum typeAdresse;
	private ClientDTO clientDTO;
	private EmployeDTO employeDTO;
	
	@Builder
	public AdresseDTO(Instant createdAt, Instant updatedAt, Long codeAdresse, String codeUniqueAdresse,
			String numeroRue, String nomRue, String codePostale, String ville, String pays,
			AdresseTypeEnum typeAdresse, ClientDTO clientDTO, EmployeDTO employeDTO) {
		super(createdAt, updatedAt);
		this.codeAdresse = codeAdresse;
		this.codeUniqueAdresse = codeUniqueAdresse;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostale = codePostale;
		this.ville = ville;
		this.pays = pays;
		this.typeAdresse = typeAdresse;
		this.clientDTO = clientDTO;
		this.employeDTO = employeDTO;
	}
	
	

	public static AdresseDTO toEntity(AdresseBean bean) {
		if(null==bean) {
			throw new CannotBeNullException("L'adresse ne peut pas être null");
		}
		return AdresseDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeAdresse(bean.getCodeAdresse())
				.codeUniqueAdresse(bean.getCodeUniqueAdresse())
				.nomRue(bean.getNomRue())
				.pays(bean.getPays())
				.ville(bean.getVille())
				.codePostale(bean.getCodePostale())
				.numeroRue(String.valueOf(bean.getNumeroRue()))
				.typeAdresse(bean.getTypeAdresse())
				.build();
	}
	
	public static AdresseBean fromEntity(AdresseDTO dto) {
		if(null==dto) {
			return null;
		}
		AdresseBean bean = AdresseBean.builder()
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.codeAdresse(dto.getCodeAdresse())
				.codeUniqueAdresse(dto.getCodeUniqueAdresse())
				.nomRue(StringUtils.isNotBlank(dto.getNomRue())?dto.getNomRue().trim().toUpperCase():null)
				.pays(StringUtils.isNotBlank(dto.getPays())?dto.getPays().trim().toUpperCase():null)
				.ville(StringUtils.isNotBlank(dto.getVille())? dto.getVille().trim().toUpperCase():null)
				.codePostale(StringUtils.isNotBlank(dto.getCodePostale())?dto.getCodePostale().trim():null)
				.build();
		try {
			bean.setNumeroRue(Integer.parseInt(dto.getNumeroRue()));
		}catch(NumberFormatException e) {
			throw new InvalidEntityException("Le numéro de la rue n'est pas valide");
		}
		
		if(AdresseTypeEnum.BILLING.equals(dto.getTypeAdresse())) {
			bean.setTypeAdresse(AdresseTypeEnum.BILLING);
		}else if(AdresseTypeEnum.SHIPPING.equals(dto.getTypeAdresse())) {
			bean.setTypeAdresse(AdresseTypeEnum.SHIPPING);
		}
		return bean;
	}
	
	
	public static AdresseResponse toAdresseResponse(AdresseDTO dto) {
		if(null==dto) {
			return null;
		}
		return AdresseResponse.builder()
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.codeAdresse(dto.getCodeAdresse())
				.codeUniqueAdresse(dto.getCodeUniqueAdresse())
				.nomRue(dto.getNomRue())
				.numeroRue(dto.getNumeroRue())
				.pays(dto.getPays())
				.ville(dto.getVille())
				.codePostale(dto.getCodePostale())
				.typeAdresse(dto.getTypeAdresse().toString())
				.build();
	}



	public static AdresseDTO adresseRequestToAdresssDTO(AdresseRequest dto) {
		if(null==dto) {
			return null;
		}
		return AdresseDTO.builder()
				.nomRue(StringUtils.isNotBlank(dto.getNomRue())? dto.getNomRue().trim().toUpperCase():null)
				.pays(StringUtils.isNotBlank(dto.getPays())? dto.getPays().trim().toUpperCase():null)
				.ville(StringUtils.isNotBlank(dto.getVille())? dto.getVille().trim().toUpperCase():null)
				.codePostale(StringUtils.isNotBlank(dto.getCodePostale())? dto.getCodePostale().trim():null)
				.typeAdresse(dto.getTypeAdresse())
				.numeroRue(dto.getNumeroRue()!=null? dto.getNumeroRue():null)
				.build();
	}
	
	
	

	
}
