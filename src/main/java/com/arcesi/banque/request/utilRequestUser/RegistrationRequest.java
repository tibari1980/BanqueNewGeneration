package com.arcesi.banque.request.utilRequestUser;

import java.util.HashSet;
import java.util.Set;

import com.arcesi.banque.entites.RoleBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Zeroual tibari Ingénieur développement
 *
 */
@Getter @Setter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

	private  String lastName;
	private String firstName;
	private  String email;
	private  String password;
	private  Set<RoleBean> roleBeans;
	
	@Builder
	public RegistrationRequest(String lastName, String firstName, String email, String password,
			Set<RoleBean> roleBeans) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.roleBeans = roleBeans;
	}
	
	 
	
	
    
}
