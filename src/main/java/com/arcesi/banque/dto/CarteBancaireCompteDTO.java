package com.arcesi.banque.dto;

/**
 * @author Mr Zeroual tibari
 * ingénieur de développement
 */
import java.time.Instant;
import java.time.LocalDate;

import com.arcesi.banque.entites.CarteBancaireCompteBean;
import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.enums.StatusCarteEnum;
import com.arcesi.banque.response.CarteBancaireCompteResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarteBancaireCompteDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;

	private Long codeCarte;
	private String codeCarteUnique;
	private String numeroCarte;
	private LocalDate dateExperation;
	private String codeVerification;
	private StatusCarteEnum statusCarte;
	private CompteDTO compteDTO;

	@Builder
	public CarteBancaireCompteDTO(Instant createdAt, Instant updatedAt, Long codeCarte, String codeCarteUnique,
			String numeroCarte, LocalDate dateExperation, String codeVerification, StatusCarteEnum statusCarte,
			CompteDTO compteDTO) {
		super(createdAt, updatedAt);
		this.codeCarte = codeCarte;
		this.codeCarteUnique = codeCarteUnique;
		this.numeroCarte = numeroCarte;
		this.dateExperation = dateExperation;
		this.codeVerification = codeVerification;
		this.statusCarte = statusCarte;
		this.compteDTO = compteDTO;
	}

	/**
	 * Méthode permettant de mapper un objet CarteBancaireCompteBean to
	 * CarteBancaireCompteDTO
	 * 
	 * @param bean {@link CarteBancaireCompteBean }
	 * @return dto {@link CarteBancaireCompteDTO }
	 */
	public static CarteBancaireCompteDTO toEntity(CarteBancaireCompteBean bean) {
		if (null != null) {
			return null;
		}
		CarteBancaireCompteDTO dto = CarteBancaireCompteDTO.builder().codeCarte(bean.getCodeCarte())
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeCarteUnique(bean.getCodeCarteUnique())
				.codeVerification(bean.getCodeVerification())
				.dateExperation(bean.getDateExperation())
				.numeroCarte(bean.getNumeroCarte())
				.statusCarte(bean.getStatusCarte()).build();

	    
		return dto;
	}

	/**
	 * Méthode permettant de mapper un objet CarteBancaireCompteDTO to
	 * CarteBancaireCompteBean
	 * 
	 * @param dto {@link CarteBancaireCompteDTO }
	 * @return bean {@link CarteBancaireCompteBean }
	 */
	public static CarteBancaireCompteBean fromEntity(CarteBancaireCompteDTO dto) {
		if (null != null) {
			return null;
		}
		CarteBancaireCompteBean bean = CarteBancaireCompteBean.builder().codeCarte(dto.getCodeCarte())
				.createdAt(dto.getCreatedAt()).updatedAt(dto.getUpdatedAt()).codeCarteUnique(dto.getCodeCarteUnique())
				.codeVerification(dto.getCodeVerification())
				.dateExperation(dto.getDateExperation())
				.numeroCarte(dto.getNumeroCarte())
				.statusCarte(dto.getStatusCarte()).build();
		if (dto.getCompteDTO() instanceof CompteCourantDTO) {
			bean.setCompteBean(CompteCourantDTO.fromEntity((CompteCourantDTO) dto.getCompteDTO()));
		} else if (dto.getCompteDTO() instanceof CompteEpargneDTO) {
			bean.setCompteBean(CompteEpargneDTO.fromEntity((CompteEpargneDTO) dto.getCompteDTO()));
		}
		return bean;
	}

	public static CarteBancaireCompteResponse toResponse(CarteBancaireCompteDTO dto) {
		CarteBancaireCompteResponse response = CarteBancaireCompteResponse.builder()
				.codeCarte(dto.getCodeCarte())
				.createdAt(dto.getCreatedAt()).updatedAt(dto.getUpdatedAt())
				.codeCarteUnique(dto.getCodeCarteUnique())
				.codeVerification(dto.getCodeVerification())
				.dateExperation(dto.getDateExperation())
				.numeroCarte(dto.getNumeroCarte())
				.statusCarte(dto.getStatusCarte()).build();
		return response;
	}
}
