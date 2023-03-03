package com.arcesi.banque.request;

import java.time.Instant;
import java.time.LocalDate;

import com.arcesi.banque.dto.CompteDTO;
import com.arcesi.banque.enums.StatusCarteEnum;

import lombok.Builder;
/**
 * @author Mr zeroual tibari
 * ingénieur développement
 */
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class CarteBancaireCompteRequest extends AbstractEntityRequest {

	
	private static final long serialVersionUID = 1L;
	private String numeroCarte;
	private LocalDate dateExperation;
	private String codeVerification;
	private StatusCarteEnum statusCarte;
	private CompteDTO compteDTO;

	@Builder
	public CarteBancaireCompteRequest(Instant createdAt, Instant updatedAt, String numeroCarte,
			LocalDate dateExperation, String codeVerification, StatusCarteEnum statusCarte, CompteDTO compteDTO) {
		super(createdAt, updatedAt);
		this.numeroCarte = numeroCarte;
		this.dateExperation = dateExperation;
		this.codeVerification = codeVerification;
		this.statusCarte = statusCarte;
		this.compteDTO = compteDTO;
	}

}
