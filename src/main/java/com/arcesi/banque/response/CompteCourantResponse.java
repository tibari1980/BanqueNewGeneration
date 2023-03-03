package com.arcesi.banque.response;

/**
 * @author mr zeroual tibari
 * ingénieur de développement
 */
import java.time.Instant;
import java.util.Collection;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class CompteCourantResponse extends CompteResponse {

	private static final long serialVersionUID = 1L;
	private double decouvert;
	
	@Builder
	public CompteCourantResponse(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique,
			double soldeCompte, CompteStatusEnumeration statusCompte, ClientResponse clientResponse,
			Collection<OperationResponse> operationResponses, EmployeResponse employeResponse,
			RibCompteResponse ribCompteResponse, CarteBancaireCompteResponse carterBancaireCompteResponse,
			EtablissementResponse etablissementResponse, double decouvert) {
		super(createdAt, updatedAt, codeCompte, codeCompteUnique, soldeCompte, statusCompte, clientResponse,
				operationResponses, employeResponse, ribCompteResponse, carterBancaireCompteResponse,
				etablissementResponse);
		this.decouvert = decouvert;
	}
	 
	

	

}
