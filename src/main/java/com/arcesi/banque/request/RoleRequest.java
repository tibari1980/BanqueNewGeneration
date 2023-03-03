package com.arcesi.banque.request;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RoleRequest extends AbstractEntityRequest {
	
	 
	private static final long serialVersionUID = 1L;
	private Long codeRole;
	private String codeUniqueRole;
	private String roleName;
	private Set<UserRequest> userRequests = new HashSet<UserRequest>();
	
	
	@Builder
	public RoleRequest(Instant createdAt, Instant updatedAt, Long codeRole, String codeUniqueRole, String roleName) {
		super(createdAt, updatedAt);
		this.codeRole = codeRole;
		this.codeUniqueRole = codeUniqueRole;
		this.roleName = roleName;
	}
	
	

}
