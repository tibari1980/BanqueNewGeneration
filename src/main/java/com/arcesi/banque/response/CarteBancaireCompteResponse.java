package com.arcesi.banque.response;

import java.time.Instant;
import java.time.LocalDate;

import com.arcesi.banque.enums.StatusCarteEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class CarteBancaireCompteResponse extends AbstractEntityResponse {

	private static final long serialVersionUID = 1L;

	private Long codeCarte;
	private String codeCarteUnique;
	private String numeroCarte;
	private LocalDate dateExperation;
	private String codeVerification;
	private StatusCarteEnum statusCarte;
	private CompteResponse compteResponse;

	@Builder
	public CarteBancaireCompteResponse(Instant createdAt, Instant updatedAt, Long codeCarte, String codeCarteUnique,
			String numeroCarte, LocalDate dateExperation, String codeVerification, StatusCarteEnum statusCarte,
			CompteResponse compteResponse) {
		super(createdAt, updatedAt);
		this.codeCarte = codeCarte;
		this.codeCarteUnique = codeCarteUnique;
		this.numeroCarte = numeroCarte;
		this.dateExperation = dateExperation;
		this.codeVerification = codeVerification;
		this.statusCarte = statusCarte;
		this.compteResponse = compteResponse;
	}

}
