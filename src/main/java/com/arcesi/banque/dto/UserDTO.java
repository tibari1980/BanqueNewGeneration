package com.arcesi.banque.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import com.arcesi.banque.entites.RoleBean;
import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.enums.MessageErrors;
import com.arcesi.banque.exceptions.InvalidEntityException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class UserDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = -6515559816600750171L;

	private Long id;
	private String uidUser;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Collection<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
	private Boolean locked = Boolean.FALSE;
	private Boolean enabled = Boolean.FALSE;
	private String ipAdresse;

	 
   @Builder
	public UserDTO(Instant createdAt, Instant updatedAt, Long id, String uidUser, String firstName, String lastName,
			String email, String password, Collection<RoleDTO> roleDTOs, Boolean locked, Boolean enabled,String ipAdresse) {
		super(createdAt, updatedAt);
		this.id = id;
		this.uidUser = uidUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roleDTOs = roleDTOs;
		this.locked = locked;
		this.enabled = enabled;
		this.ipAdresse=ipAdresse;
	}
	
   /**
    * Méthode permettant de mapper un objet UserBean to UserDTO
    * @param bean {@link UserBean }
    * @return bean {@link UserDTO}
    */
	public static UserDTO toEntity(UserBean bean) {
		if(null==bean) {
			return null;
		}
		UserDTO dto= UserDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.updatedAt)
				.email(bean.getEmail())
				.enabled(bean.getEnabled())
				.firstName(bean.getFirstName())
				.id(bean.getId())
				.lastName(bean.getLastName())
				.uidUser(bean.getUidUser())
				.password(bean.getPassword())
				.ipAdresse(bean.getIpAdresse())
				.build();
		if(CollectionUtils.isNotEmpty(bean.getRoleBeans())) {
			Collection<RoleDTO> rolesDtos=new ArrayList<RoleDTO>();
			for(RoleBean b:bean.getRoleBeans()) {
				rolesDtos.add(RoleDTO.toEntity(b));
			}
			if(CollectionUtils.isNotEmpty(rolesDtos)) {
				dto.setRoleDTOs(rolesDtos);
			}
		}
		return dto;
	}
	
	/**
	    * Méthode permettant de mapper un objet UserBean to UserDTO
	    * @param bean {@link UserBean }
	    * @return bean {@link UserDTO}
	    */
	public static UserBean fromEntity(UserDTO bean) {
		if (null == bean) {
			throw new InvalidEntityException(MessageErrors.USER_NOT_VALID.getCode());
		}
		UserBean b=UserBean.builder().createdAt(bean.getCreatedAt()).updatedAt(bean.updatedAt)
				.email(bean.getEmail())
				.enabled(bean.getEnabled())
				.firstName(bean.getFirstName())
				.id(bean.getId())
				.lastName(bean.getLastName())
				.uidUser(bean.getUidUser())
				.password(bean.getPassword())
				.ipAdresse(bean.getIpAdresse())
				.build();
		
		if(CollectionUtils.isNotEmpty(bean.getRoleDTOs())) {
			Collection<RoleBean> rolesBeans=new ArrayList<RoleBean>();
			for(RoleDTO r:bean.getRoleDTOs()) {
				rolesBeans.add(RoleDTO.fromEntity(r));
			}
			if(CollectionUtils.isNotEmpty(rolesBeans)) {
				b.setRoleBeans(rolesBeans);
			}
		}
		
		return b;
	}

	



	
}
