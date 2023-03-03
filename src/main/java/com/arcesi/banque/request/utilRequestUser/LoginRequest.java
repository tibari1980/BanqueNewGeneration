package com.arcesi.banque.request.utilRequestUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
* 
* @author Zeroual tibari Ingénieur développement
*
*/
@Data
@EqualsAndHashCode
@ToString
public class LoginRequest {

	
	private  String email;
	private  String password;
}
