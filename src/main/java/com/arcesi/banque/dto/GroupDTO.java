package com.arcesi.banque.dto;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import com.arcesi.banque.entites.GroupBean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class GroupDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;

	private Long codeGroupe;
	private String codeGroupUnique;
	private String libelleGroup;
	private Collection<EmployeDTO> employeDTOs;
	
	@Builder
	public GroupDTO(Instant createdAt, Instant updatedAt, Long codeGroupe, String codeGroupUnique, String libelleGroup,
			Collection<EmployeDTO> employeDTOs) {
		super(createdAt, updatedAt);
		this.codeGroupe = codeGroupe;
		this.codeGroupUnique = codeGroupUnique;
		this.libelleGroup = libelleGroup;
		this.employeDTOs = employeDTOs;
	}

	/**
	 * Méthode permettant de mapper un objet GroupBean to GroupDTO
	 * @param bean {@link GroupBean  }
	 * @return bean {@link GroupDTO }
	 */
	public static GroupDTO toEntity(GroupBean bean) {
		if(null==bean) {
			return null;
		}
		return GroupDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeGroupe(bean.getCodeGroupe())
				.codeGroupUnique(bean.getCodeGroupUnique())
				.libelleGroup(bean.getLibelleGroup())
				.employeDTOs(
						bean.getEmployeBeans()!=null? bean.getEmployeBeans().stream().map(EmployeDTO::toEntity).collect(Collectors.toList()):null
						)
				.build();
	}

	/**
	 * Méthode permettant de mapper un objet GroupDTO to GroupBean
	 * @param bean {@link GroupDTO  }
	 * @return bean {@link GroupBean }
	 */
	public static GroupBean fromEntity(GroupDTO bean) {
		if(null==bean) {
			return null;
		}
		return GroupBean.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeGroupe(bean.getCodeGroupe())
				.codeGroupUnique(bean.getCodeGroupUnique())
				.libelleGroup(bean.getLibelleGroup())
				.employeBeans(
						bean.getEmployeDTOs()!=null?
								bean.getEmployeDTOs().stream().map(EmployeDTO::fromEntity).collect(Collectors.toSet()):null
						)
				.build();
	}
}
