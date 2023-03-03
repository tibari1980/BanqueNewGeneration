package com.arcesi.banque.response;

/**
 * @author Mr zeorual tibari
 * Ingénieur de developpement
 */
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public abstract class CompteResponse extends AbstractEntityResponse {

	private static final long serialVersionUID = 1L;
	private Long codeCompte;
	private String codeCompteUnique;
	private double soldeCompte;
	private CompteStatusEnumeration statusCompte;
	private ClientResponse clientResponse;
	private Collection<OperationResponse> operationResponses;
	private EmployeResponse employeResponse;
	private RibCompteResponse ribCompteResponse;
	public CarteBancaireCompteResponse carterBancaireCompteResponse;
	private EtablissementResponse etablissementResponse;
	public CompteResponse(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique,
			double soldeCompte, CompteStatusEnumeration statusCompte, ClientResponse clientResponse,
			Collection<OperationResponse> operationResponses, EmployeResponse employeResponse,
			RibCompteResponse ribCompteResponse, CarteBancaireCompteResponse carterBancaireCompteResponse,
			EtablissementResponse etablissementResponse) {
		super(createdAt, updatedAt);
		this.codeCompte = codeCompte;
		this.codeCompteUnique = codeCompteUnique;
		this.soldeCompte = soldeCompte;
		this.statusCompte = statusCompte;
		this.clientResponse = clientResponse;
		this.operationResponses = new ArrayList<OperationResponse>();
		this.employeResponse = employeResponse;
		this.ribCompteResponse = ribCompteResponse;
		this.carterBancaireCompteResponse = carterBancaireCompteResponse;
		this.etablissementResponse = etablissementResponse;
	}

	
}
