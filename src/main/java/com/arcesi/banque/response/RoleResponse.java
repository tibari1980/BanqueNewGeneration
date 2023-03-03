package com.arcesi.banque.response;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RoleResponse  extends AbstractEntityResponse{

	private static final long serialVersionUID = 1L;
	private Long codeRole;
	private String codeUniqueRole;
	private String roleName;
	@Builder
	public RoleResponse(Instant createdAt, Instant updatedAt, Long codeRole, String codeUniqueRole, String roleName) {
		super(createdAt, updatedAt);
		this.codeRole = codeRole;
		this.codeUniqueRole = codeUniqueRole;
		this.roleName = roleName;
	}
	
	
	
}
