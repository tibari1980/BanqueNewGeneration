package com.arcesi.banque.request;

import java.time.Instant;
import java.util.Collection;

import com.arcesi.banque.dto.EmployeDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class GroupRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 1L;
	private String libelleGroup;
	private Collection<EmployeDTO> employeDTOs;

	@Builder
	public GroupRequest(Instant createdAt, Instant updatedAt, String libelleGroup) {
		super(createdAt, updatedAt);
		this.libelleGroup = libelleGroup;
	}

}
