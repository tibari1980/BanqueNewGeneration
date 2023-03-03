package com.arcesi.banque.response;

import java.time.Instant;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class GroupResponse extends AbstractEntityResponse {

	private static final long serialVersionUID = 1L;

	private Long codeGroupe;
	private String codeGroupUnique;
	private String libelleGroup;
	private Collection<EmployeResponse> employeResponses;

	@Builder
	public GroupResponse(Instant createdAt, Instant updatedAt, Long codeGroupe, String codeGroupUnique,
			String libelleGroup, Collection<EmployeResponse> employeResponses) {
		super(createdAt, updatedAt);
		this.codeGroupe = codeGroupe;
		this.codeGroupUnique = codeGroupUnique;
		this.libelleGroup = libelleGroup;
		this.employeResponses = employeResponses;
	}

}
