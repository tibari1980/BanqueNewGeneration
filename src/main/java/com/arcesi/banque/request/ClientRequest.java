package com.arcesi.banque.request;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

import com.arcesi.banque.enums.SexEnumeration;
import com.arcesi.banque.enums.TypeClientEnumeration;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class ClientRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 1L;
	private String typeClient;
	private String sex;
	private String nomClient;
	private String prenomClient;
	private String emailClient;
	private String telephoneClient;
	private String photoClient;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String dateNaissanceClient;
	private String paysNaissance;
	private String villeNaissance;
	private String departementNaissance;
	private String raisonSocial;
	private String numeroSiret;
	private Collection<AdresseRequest> adresseRequests;

	@Builder
	public ClientRequest(Instant createdAt, Instant updatedAt, String typeClient,String sex,
			String nomClient, String prenomClient, String emailClient, String telephoneClient, String photoClient,
			String dateNaissanceClient, String paysNaissance, String villeNaissance,
			String departementNaissance, String raisonSocial, String numeroSiret) {
		super(createdAt, updatedAt);
		this.typeClient = typeClient;
		this.sex = sex;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.telephoneClient = telephoneClient;
		this.photoClient = photoClient;
		this.dateNaissanceClient = dateNaissanceClient;
	 
		this.paysNaissance = paysNaissance;
		this.villeNaissance = villeNaissance;
		this.departementNaissance = departementNaissance;
		this.raisonSocial = raisonSocial;
		this.numeroSiret = numeroSiret;
	}

}
