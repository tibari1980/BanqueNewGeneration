package com.arcesi.banque.request;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 6533116596754134330L;
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
	private Collection<CompteRequest> compteRequests;
	private Collection<AdresseRequest> adresseRequests;
	private EmployeRequest employeRequestSup;
	private Collection<GroupRequest> groupRequests;

	@Builder
	public EmployeRequest(Instant createdAt, Instant updatedAt, String nomEmploye, String prenomEmploye,
			String emailEmploye, String telephoneEmploye, String photoEmploye, LocalDate dateNaissanceEmploye,
			Integer ageEmploye, String paysNaissance, String villeNaissance, String departementNaissance,
			EmployeRequest employeRequestSup) {
		super(createdAt, updatedAt);
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
		this.employeRequestSup = employeRequestSup;
	}

}
