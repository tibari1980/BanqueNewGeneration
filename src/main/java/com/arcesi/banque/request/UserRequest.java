package com.arcesi.banque.request;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserRequest  extends AbstractEntityRequest{
	
	 
	private static final long serialVersionUID = 1L;
	private String uidUser;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Set<RoleRequest> roleRequests = new HashSet<RoleRequest>();
	private Boolean locked = Boolean.FALSE;
	private Boolean enabled = Boolean.FALSE;
	
	@Builder
	public UserRequest(Instant createdAt, Instant updatedAt, String uidUser, String firstName, String lastName,
			String email, String password, Set<RoleRequest> roleRequests, Boolean locked, Boolean enabled) {
		super(createdAt, updatedAt);
		this.uidUser = uidUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roleRequests = roleRequests;
		this.locked = locked;
		this.enabled = enabled;
	}
	
	

}
