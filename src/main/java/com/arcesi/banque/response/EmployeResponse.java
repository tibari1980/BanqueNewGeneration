package com.arcesi.banque.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class EmployeResponse extends AbstractEntityResponse {

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
	private Collection<CompteResponse> compteResponses;
	private Collection<AdresseResponse> adresseResponses;
	 
	private EmployeResponse employeDTOSup;
	private Collection<GroupResponse> groupResponses;

	@Builder
	public EmployeResponse(Instant createdAt, Instant updatedAt, Long code, String codeEmployeUnique, String nomEmploye,
			String prenomEmploye, String emailEmploye, String telephoneEmploye, String photoEmploye,
			LocalDate dateNaissanceEmploye, Integer ageEmploye, String paysNaissance, String villeNaissance,
			String departementNaissance, Collection<CompteResponse> compteResponses,
			Collection<AdresseResponse> adresseResponses,
			EmployeResponse employeDTOSup, Collection<GroupResponse> groupResponses) {
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
		this.compteResponses = compteResponses;
		this.adresseResponses = adresseResponses;
		this.employeDTOSup = employeDTOSup;
		this.groupResponses = groupResponses;
	}

}
