package com.arcesi.banque.response;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserResponse  extends AbstractEntityResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String uidUser;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Set<RoleResponse> roleResponses = new HashSet<RoleResponse>();
	private Boolean locked ;
	private Boolean enabled;
	
	@Builder
	public UserResponse(Instant createdAt, Instant updatedAt, Long id, String uidUser, String firstName,
			String lastName, String email, String password, Set<RoleResponse> roleResponses, Boolean locked,
			Boolean enabled) {
		super(createdAt, updatedAt);
		this.id = id;
		this.uidUser = uidUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roleResponses = roleResponses;
		this.locked = locked;
		this.enabled = enabled;
	}
	
	
}
