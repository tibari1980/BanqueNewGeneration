package com.arcesi.banque.response;

import java.time.Instant;

import com.arcesi.banque.enums.TypeOperationEnumeration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class OperationResponse extends AbstractEntityResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codeOperation;
	private String codeUniqueOperation;
	private double montantOperation;
	private String libelleOperation;
	private TypeOperationEnumeration typeOperation;
	private CompteResponse compteResponse;
	private EmployeResponse employeResponse;

	@Builder
	public OperationResponse(Instant createdAt, Instant updatedAt, Long codeOperation, String codeUniqueOperation,
			double montantOperation, String libelleOperation, TypeOperationEnumeration typeOperation,
			CompteResponse compteResponse, EmployeResponse employeResponse) {
		super(createdAt, updatedAt);
		this.codeOperation = codeOperation;
		this.codeUniqueOperation = codeUniqueOperation;
		this.montantOperation = montantOperation;
		this.libelleOperation = libelleOperation;
		this.typeOperation = typeOperation;
		this.compteResponse = compteResponse;
		this.employeResponse = employeResponse;
	}

}
