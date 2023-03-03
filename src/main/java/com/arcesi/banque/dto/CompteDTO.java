package com.arcesi.banque.dto;
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
@Setter @ToString
public class CompteDTO extends AbstractEntityDTO {

	 
	private static final long serialVersionUID = 1L;
	private Long codeCompte;
	private String codeCompteUnique;
	private double soldeCompte;
	private CompteStatusEnumeration statusCompte;
	private ClientDTO clientDTO;
	private Collection<OperationDTO> operationDTOs;
	private EmployeDTO employeDTO;
	private RibCompteDTO ribCompteDTO;
	public CarteBancaireCompteDTO carteDTO;
	private EtablissementDTO etablissementDTO;
	
	
	public CompteDTO(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique, double soldeCompte,
			CompteStatusEnumeration statusCompte, ClientDTO clientDTO, Collection<OperationDTO> operationDTOs,
			EmployeDTO employeDTO, RibCompteDTO ribCompteDTO, CarteBancaireCompteDTO carteDTO,
			EtablissementDTO etablissementDTO) {
		super(createdAt, updatedAt);
		this.codeCompte = codeCompte;
		this.codeCompteUnique = codeCompteUnique;
		this.soldeCompte = soldeCompte;
		this.statusCompte = statusCompte;
		this.clientDTO = clientDTO;
		this.operationDTOs = operationDTOs;
		this.employeDTO = employeDTO;
		this.ribCompteDTO = ribCompteDTO;
		this.carteDTO = carteDTO;
		this.etablissementDTO = etablissementDTO;
	}

	

}
