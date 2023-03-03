package com.arcesi.banque.response;

import java.time.Instant;

import com.arcesi.banque.dto.RibCompteDTO;
import com.arcesi.banque.entites.RibCompteBean;
import com.arcesi.banque.exceptions.InvalidEntityException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class RibCompteResponse extends AbstractEntityResponse {

	private static final long serialVersionUID = 1L;

	private Long codeRib;
	private String codeRibUnique;
	private String etablissement; // 20041
	private String guichet; // 00001
	private String numeroCompte; // 6223900P020
	private String cleRib;
	private String domiciliationCompte;
	private String ibanCompte;
	private String bicCompte;
	private CompteResponse compteResponse;
	
	@Builder
	public RibCompteResponse(Instant createdAt, Instant updatedAt, Long codeRib, String codeRibUnique,
			String etablissement, String guichet, String numeroCompte, String cleRib, String domiciliationCompte,
			String ibanCompte, String bicCompte, CompteResponse compteResponse) {
		super(createdAt, updatedAt);
		this.codeRib = codeRib;
		this.codeRibUnique = codeRibUnique;
		this.etablissement = etablissement;
		this.guichet = guichet;
		this.numeroCompte = numeroCompte;
		this.cleRib = cleRib;
		this.domiciliationCompte = domiciliationCompte;
		this.ibanCompte = ibanCompte;
		this.bicCompte = bicCompte;
		this.compteResponse = compteResponse;
	}

	
	public static RibCompteDTO toEntity(RibCompteBean bean) {
		if (null == bean) {
		    throw new InvalidEntityException("L'objet ne peut pas être null");
		}
		RibCompteDTO dto = RibCompteDTO.builder()
				.codeRib(bean.getCodeRib())
				.codeRibUnique(bean.getCodeRibUnique())
				.cleRib(bean.getCleRib())
				.bicCompte(bean.getBicCompte())
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.etablissement(bean.getEtablissement())
				.guichet(bean.getGuichet())
				.numeroCompte(bean.getNumeroCompte())
				.domiciliationCompte(bean.getDomiciliationCompte())
				.build();
	    
		return dto;
	}
	
	
	public static RibCompteBean fromEntity(RibCompteDTO bean) {
		if (null == bean) {
		    throw new InvalidEntityException("L'objet ne peut pas être null");
		}
		RibCompteBean dto = RibCompteBean.builder()
				.codeRib(bean.getCodeRib())
				.codeRibUnique(bean.getCodeRibUnique())
				.cleRib(bean.getCleRib())
				.bicCompte(bean.getBicCompte())
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.etablissement(bean.getEtablissement())
				.guichet(bean.getGuichet())
				.numeroCompte(bean.getNumeroCompte())
				.domiciliationCompte(bean.getDomiciliationCompte())
				.build();
		return dto;
	}
	
	
	public static RibCompteResponse toResponse(RibCompteDTO bean) {
		if (null == bean) {
		    throw new InvalidEntityException("L'objet ne peut pas être null");
		}
		RibCompteResponse dto = RibCompteResponse.builder()
				.codeRib(bean.getCodeRib())
				.codeRibUnique(bean.getCodeRibUnique())
				.cleRib(bean.getCleRib())
				.bicCompte(bean.getBicCompte())
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.etablissement(bean.getEtablissement())
				.guichet(bean.getGuichet())
				.numeroCompte(bean.getNumeroCompte())
				.domiciliationCompte(bean.getDomiciliationCompte())
				.build();
		return dto;
	}
	
	
	
	

}
