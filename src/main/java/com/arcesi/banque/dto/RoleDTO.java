package com.arcesi.banque.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arcesi.banque.entites.RoleBean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;
	private Long codeRole;
	private String codeUniqueRole;
	private String roleName;
 
	 @Builder
		public RoleDTO(Instant createdAt, Instant updatedAt, Long codeRole, String codeUniqueRole, String roleName) {
			super(createdAt, updatedAt);
			this.codeRole = codeRole;
			this.codeUniqueRole = codeUniqueRole;
			this.roleName = roleName;
 		} 
	
	 /**
	  * Méthode permettant de mapper un objet RoleBean to RoleDto
	  * @param bean {@link  RoleBean }
	  * @return dto {@link RoleDTO }
	  */
	public static RoleDTO toEntity(RoleBean bean) {
		if(null==bean) {
			return null;
		}
		return RoleDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeRole(bean.getCodeRole())
				.codeUniqueRole(bean.getCodeUniqueRole())
				.roleName(bean.getRoleName())
				 
				.build();
	}


	 /**
	  * Méthode permettant de mapper un objet RoleDTO to RoleBean
	  * @param bean {@link  RoleDTO }
	  * @return bean {@link RoleBean }
	  */
	public static RoleBean fromEntity(RoleDTO bean) {
		if(null==bean) {
			return null;
		}
		return RoleBean.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeRole(bean.getCodeRole())
				.codeUniqueRole(bean.getCodeUniqueRole())
				.roleName(bean.getRoleName())
				 
				.build();
	}

   
}
