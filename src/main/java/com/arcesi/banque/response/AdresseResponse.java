package com.arcesi.banque.response;

import java.time.Instant;

import com.arcesi.banque.enums.AdresseTypeEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class AdresseResponse extends AbstractEntityResponse {

	public AdresseResponse(Instant createdAt, Instant updatedAt) {
		super(createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	private Long codeAdresse;
	private String codeUniqueAdresse;
	private String numeroRue;
	private String nomRue;
	private String codePostale;
	private String ville;
	private String pays;
	private String typeAdresse;
	private ClientResponse clientResponse;
	private EmployeResponse employeResponse;

	@Builder
	public AdresseResponse(Instant createdAt, Instant updatedAt, Long codeAdresse, String codeUniqueAdresse,
			String numeroRue, String nomRue, String codePostale, String ville, String pays,
			String typeAdresse, ClientResponse clientResponse, EmployeResponse employeResponse) {
		super(createdAt, updatedAt);
		this.codeAdresse = codeAdresse;
		this.codeUniqueAdresse = codeUniqueAdresse;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostale = codePostale;
		this.ville = ville;
		this.pays = pays;
		this.typeAdresse = typeAdresse;
		this.clientResponse = clientResponse;
		this.employeResponse = employeResponse;
	}

}
