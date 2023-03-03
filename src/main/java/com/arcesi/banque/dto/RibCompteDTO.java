package com.arcesi.banque.dto;

/**
 * @author Mr Zeroual tibari
 * ingénieur de développement
 */
import java.time.Instant;

import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.entites.RibCompteBean;
import com.arcesi.banque.exceptions.InvalidEntityException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RibCompteDTO extends AbstractEntityDTO {

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
	private CompteDTO compteDTO;

	@Builder
	public RibCompteDTO(Instant createdAt, Instant updatedAt, Long codeRib, String codeRibUnique, String etablissement,
			String guichet, String numeroCompte, String cleRib, String domiciliationCompte, String ibanCompte,
			String bicCompte, CompteDTO compteDTO) {
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
	}

	/**
	 * Méthode permettant de mapper un objet RibCompteBean to RibCompteDTO
	 * 
	 * @param bean {@link RibCompteBean }
	 * @return bean {@link RibCompteDTO }
	 */
	public static RibCompteDTO toEntity(RibCompteBean bean) {
		if (null == bean) {
			throw new InvalidEntityException("L'objet n'est pas valide ");
		}
		RibCompteDTO dto = RibCompteDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeRib(bean.getCodeRib())
				.codeRibUnique(bean.getCodeRibUnique())
				.etablissement(bean.getEtablissement())
				.guichet(bean.getGuichet())
				.cleRib(bean.getCleRib())
				.numeroCompte(bean.getNumeroCompte())
				.domiciliationCompte(bean.getDomiciliationCompte())
				.ibanCompte(bean.getIbanCompte()).bicCompte(bean.getBicCompte()).build();
		
		return dto;
	}

	/**
	 * Méthode permettant de mapper un objet RibCompteDTO to RibCompteBean
	 * 
	 * @param bean {@link RibComptDTO }
	 * @return bean {@link RibCompteBean }
	 */
	public static RibCompteBean fromEntity(RibCompteDTO bean) {
		if (null == bean) {
			return null;
		}
		RibCompteBean dto = RibCompteBean.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeRib(bean.getCodeRib())
				.codeRibUnique(bean.getCodeRibUnique())
				.etablissement(bean.getEtablissement())
				.guichet(bean.getGuichet()).cleRib(bean.getCleRib())
				.numeroCompte(bean.getNumeroCompte())
				.domiciliationCompte(bean.getDomiciliationCompte())
				.ibanCompte(bean.getIbanCompte())
				.bicCompte(bean.getBicCompte())
				.build();
		
		if (bean.getCompteDTO() instanceof CompteCourantDTO) {
			dto.setCompteBean(CompteCourantDTO.fromEntity((CompteCourantDTO) bean.getCompteDTO()));
		} else if (bean.getCompteDTO() instanceof CompteEpargneDTO) {
			dto.setCompteBean(CompteEpargneDTO.fromEntity((CompteEpargneDTO) bean.getCompteDTO()));
		}
		return dto;
	}

}
