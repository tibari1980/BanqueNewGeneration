package com.arcesi.banque.request;

import java.time.Instant;

import com.arcesi.banque.enums.AdresseTypeEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author ZEROUAL Tibari ingénieur develpppement
 */
@Getter
@Setter @ToString
public class AdresseRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 1L;
	
	private String numeroRue;
	private String nomRue;
	private String codePostale;
	private String ville;
	private String pays;
	private AdresseTypeEnum typeAdresse;
	private ClientRequest clientRequest;
	private EmployeRequest employeRequest;

	@Builder
	public AdresseRequest(Instant createdAt, Instant updatedAt, String numeroRue, String nomRue, String codePostale,
			String ville, String pays, AdresseTypeEnum typeAdresse, ClientRequest clientRequest,
			EmployeRequest employeRequest)  {
		super(createdAt, updatedAt);
		 
		    
		this.numeroRue=numeroRue;
		this.nomRue = nomRue;
		this.codePostale = codePostale;
		this.ville = ville;
		this.pays = pays;
		this.typeAdresse = typeAdresse;
		this.clientRequest = clientRequest;
		this.employeRequest = employeRequest;
	}

}
