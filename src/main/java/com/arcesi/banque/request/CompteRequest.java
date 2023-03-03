package com.arcesi.banque.request;
/**
 * @author Mr zeorual tibari
 * Ingénieur de developpement
 */
import java.time.Instant;
import java.util.Collection;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter  @ToString
public abstract class CompteRequest extends AbstractEntityRequest {

	 
	private static final long serialVersionUID = 1L;
	 
	private double soldeCompte;
	private CompteStatusEnumeration statusCompte;
	private ClientRequest clientRequest;
	private Collection<OperationRequest> operationRequests;
	private EmployeRequest employeRequest;
	private RibCompteRequest ribCompteRequest;
	public CarteBancaireCompteRequest carteRequest;
	private EtablissementRequest etablissementRequest;
	
	
	public CompteRequest(Instant createdAt, Instant updatedAt, double soldeCompte, CompteStatusEnumeration statusCompte,
			ClientRequest clientRequest, Collection<OperationRequest> operationRequests, EmployeRequest employeRequest,
			RibCompteRequest ribCompteRequest, CarteBancaireCompteRequest carteRequest,
			EtablissementRequest etablissementRequest) {
		super(createdAt, updatedAt);
		this.soldeCompte = soldeCompte;
		this.statusCompte = statusCompte;
		this.clientRequest = clientRequest;
		this.operationRequests = operationRequests;
		this.employeRequest = employeRequest;
		this.ribCompteRequest = ribCompteRequest;
		this.carteRequest = carteRequest;
		this.etablissementRequest = etablissementRequest;
	}
	
	 
	

}
