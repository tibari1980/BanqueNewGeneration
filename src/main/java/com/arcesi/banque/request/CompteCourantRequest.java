package com.arcesi.banque.request;

/**
 * @author Mr zeroual tibari
 * ingénieur developpement
 */
import java.time.Instant;
import java.util.Collection;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter  @ToString
public class CompteCourantRequest extends CompteRequest {

	private static final long serialVersionUID = 1L;
	private double decouvert;

	@Builder
	public CompteCourantRequest(Instant createdAt, Instant updatedAt, double soldeCompte,
			CompteStatusEnumeration statusCompte, ClientRequest clientRequest,
			Collection<OperationRequest> operationRequests, EmployeRequest employeRequest,
			RibCompteRequest ribCompteRequest, CarteBancaireCompteRequest carteRequest,
			EtablissementRequest etablissementRequest, double decouvert) {
		super(createdAt, updatedAt, soldeCompte, statusCompte, clientRequest, operationRequests, employeRequest,
				ribCompteRequest, carteRequest, etablissementRequest);
		this.decouvert = decouvert;
	}

}
