package com.arcesi.banque.dto;

/**
 * @author Mr zeroual tibari
 * ingénieur de develppement
 */
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.arcesi.banque.entites.CompteBean;
import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.entites.EmployeBean;
import com.arcesi.banque.entites.EtablissementBean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class EtablissementDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;

	private String codeEtablissement;
	private String codeEtablissementUnique;
	private String codeGuiche;
	private String libelleEtablissement;
	private String emailEtablissement;
	private String telephoneEtablissement;
	private String faxEtablissement;
	private String domiciliation;
	private Collection<CompteDTO> compteDTOs;

	@Builder
	public EtablissementDTO(Instant createdAt, Instant updatedAt, String codeEtablissement,
			String codeEtablissementUnique, String codeGuiche, String libelleEtablissement, String emailEtablissement,
			String telephoneEtablissement, String faxEtablissement, String domiciliation,
			Collection<CompteDTO> compteDTOs) {
		super(createdAt, updatedAt);
		this.codeEtablissement = codeEtablissement;
		this.codeEtablissementUnique = codeEtablissementUnique;
		this.codeGuiche = codeGuiche;
		this.libelleEtablissement = libelleEtablissement;
		this.emailEtablissement = emailEtablissement;
		this.telephoneEtablissement = telephoneEtablissement;
		this.faxEtablissement = faxEtablissement;
		this.domiciliation = domiciliation;
		this.compteDTOs = compteDTOs;
	}
	/**
	 * Méthode permettant de mapper un objet  EtablissementBean to EtablissementDTO
	 * @param bean {@link EtablissementBean}
	 * @return  bean {@link EmployeDTO}
	 */

	public static EtablissementDTO toEntity(EtablissementBean bean) {
		if(null==bean) {
			return null;
		}
		EtablissementDTO dto= EtablissementDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeEtablissement(bean.getCodeEtablissement())
				.codeEtablissementUnique(bean.getCodeEtablissementUnique())
				.codeGuiche(bean.getCodeGuiche())
				.domiciliation(bean.getDomiciliation())
				.emailEtablissement(bean.getEmailEtablissement())
				.faxEtablissement(bean.getFaxEtablissement())
				.telephoneEtablissement(bean.getTelephoneEtablissement())
				.libelleEtablissement(bean.getLibelleEtablissement())
				.build();
	
	
//		if(bean.getCompteBeans()!=null) {
//			List<CompteDTO> comptedtos=new ArrayList<CompteDTO>();
//			for(CompteBean compte:bean.getCompteBeans()) {
//				if(compte instanceof  CompteCourantBean) {
//					 comptedtos.add(CompteCourantDTO.toEntity((CompteCourantBean) compte));
//				}else if(compte instanceof CompteEpargneBean) {
//					 comptedtos.add(CompteEpargneDTO.toEntity((CompteEpargneBean) compte));
//				}
//			}
//			if(comptedtos!=null) {
//		        dto.setCompteDTOs(
//		        		comptedtos
//		        		);
//		        
//			}
//		}
		return dto;
	}


	/**
	 * Méthode permettant de mapper un objet EtablissementDTO to EtablissementBean
	 * @param bean {@link EtablissementDTO}
	 * @return  bean {@link EmployeBean}
	 */

	public static EtablissementBean fromEntity(EtablissementDTO bean) {
		if(null==bean) {
			return null;
		}
		EtablissementBean dto= EtablissementBean.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeEtablissement(bean.getCodeEtablissement())
				.codeEtablissementUnique(bean.getCodeEtablissementUnique())
				.codeGuiche(bean.getCodeGuiche())
				.domiciliation(bean.getDomiciliation())
				.emailEtablissement(bean.getEmailEtablissement())
				.faxEtablissement(bean.getFaxEtablissement())
				.telephoneEtablissement(bean.getTelephoneEtablissement())
				.libelleEtablissement(bean.getLibelleEtablissement())
				.build();
	
//		if (bean.getCompteDTOs() != null) {
//			List<CompteBean> comptebeans = new ArrayList<CompteBean>();
//			for (CompteDTO compte : bean.getCompteDTOs()) {
//				if (compte instanceof CompteCourantDTO) {
//					comptebeans.add(CompteCourantDTO.fromEntity((CompteCourantDTO) compte));
//				} else if (compte instanceof CompteEpargneDTO) {
//					comptebeans.add(CompteEpargneDTO.fromEntity((CompteEpargneDTO) compte));
//				}
//			}
//			if (comptebeans != null) {
//				dto.setCompteBeans(comptebeans);
//			}
//		}
	  return dto;
	}

	
}
