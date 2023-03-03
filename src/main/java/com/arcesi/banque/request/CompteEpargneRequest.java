package com.arcesi.banque.request;

import java.time.Instant;
import java.util.Collection;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.Builder;
/**
 * @author Mr zeroual tibari
 * Ingénieur de developpement
 */
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter  @ToString
public class CompteEpargneRequest extends CompteRequest {

	private static final long serialVersionUID = 1L;
	private double tauxInteret;

	@Builder
	public CompteEpargneRequest(Instant createdAt, Instant updatedAt, double soldeCompte,
			CompteStatusEnumeration statusCompte, ClientRequest clientRequest,
			Collection<OperationRequest> operationRequests, EmployeRequest employeRequest,
			RibCompteRequest ribCompteRequest, CarteBancaireCompteRequest carteRequest,
			EtablissementRequest etablissementRequest, double tauxInteret) {
		super(createdAt, updatedAt, soldeCompte, statusCompte, clientRequest, operationRequests, employeRequest,
				ribCompteRequest, carteRequest, etablissementRequest);
		this.tauxInteret = tauxInteret;
	}

}
