package com.arcesi.banque.request;

import java.time.Instant;

import com.arcesi.banque.dto.CompteDTO;
import com.arcesi.banque.dto.EmployeDTO;
import com.arcesi.banque.enums.TypeOperationEnumeration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author ARCESI Mr Zeroual tibari ingénieur développement
 *
 */
@Getter
@Setter  @ToString
public class OperationRequest extends AbstractEntityRequest {

	 
	private static final long serialVersionUID = 1L;
	private double montantOperation;
	private String libelleOperation;
	private TypeOperationEnumeration typeOperation;
	private CompteDTO compteDTO;
	private EmployeDTO employeDTO;

	@Builder
	public OperationRequest(Instant createdAt, Instant updatedAt, double montantOperation, String libelleOperation,
			TypeOperationEnumeration typeOperation, CompteDTO compteDTO, EmployeDTO employeDTO) {
		super(createdAt, updatedAt);
		this.montantOperation = montantOperation;
		this.libelleOperation = libelleOperation;
		this.typeOperation = typeOperation;
		this.compteDTO = compteDTO;
		this.employeDTO = employeDTO;
	}

}
