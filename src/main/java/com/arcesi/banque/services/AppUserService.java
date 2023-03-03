package com.arcesi.banque.services;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.arcesi.banque.dto.RoleDTO;
import com.arcesi.banque.dto.UserDTO;
/**
 * @author Mr Zeroual tibari
 * Ingénieur de developpement
 */
import com.arcesi.banque.request.utilRequestUser.RegistrationRequest;

public interface AppUserService extends UserDetailsService{

	String register(RegistrationRequest registrationRequest);

    boolean enableAppUser(String email);
    
    
    public UserDTO saveUser(UserDTO userDTO);

	public UserDTO findById(final Long id);

	public UserDTO updateUser(final Long id, UserDTO userDTO);

	public void deleteUser(final Long id);
	
	public UserDTO loadUserByemail(String email);
	
	public RoleDTO saveRole(final RoleDTO roleDTO);
	
	public RoleDTO findRoleBeanById(final Long id);
	
	public RoleDTO updateRole(final Long id, RoleDTO roleDTO);
	
	public void addRoleToUser(String email,String roleName);
	
	public void deleteRole(Long id);
	
	public UserDTO loadUserByUserName(String email);
	

}
