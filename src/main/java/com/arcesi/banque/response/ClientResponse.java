package com.arcesi.banque.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.arcesi.banque.enums.SexEnumeration;
import com.arcesi.banque.enums.TypeClientEnumeration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class ClientResponse extends AbstractEntityResponse {

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
	private LocalDate dateNaissanceClient;
	private Integer ageClient;
	private String paysNaissance;
	private String villeNaissance;
	private String departementNaissance;
	private String raisonSocial;
	private String numeroSiret;
	private Collection<CompteResponse> compteResponses;
	private List<AdresseResponse> adresseResponses;
	
	@Builder
	public ClientResponse(Instant createdAt, Instant updatedAt, Long code, String codeClientUnique,
			TypeClientEnumeration typeClient, SexEnumeration sex, String nomClient, String prenomClient,
			String emailClient, String telephoneClient, String photoClient, LocalDate dateNaissanceClient,
			Integer ageClient, String paysNaissance, String villeNaissance, String departementNaissance,
			String raisonSocial, String numeroSiret, Collection<CompteResponse> compteResponses,
			List<AdresseResponse> adresseResponses) {
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
		this.compteResponses = compteResponses;
		this.adresseResponses = adresseResponses;
	}

	

}
