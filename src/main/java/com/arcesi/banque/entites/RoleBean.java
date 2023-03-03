package com.arcesi.banque.entites;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "ROLE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_ROLE", name = "CODE_ROLE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_ROLE_UNIQUE", name = "CODE_ROLE_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleBean  extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "role_sequence", name = "role_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
	@Column(name = "CODE_ROLE", nullable = false, unique = true)
	private Long codeRole;
	@Column(name = "CODE_ROLE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeUniqueRole;
	@Column(name="ROLE_NAME",unique = true,nullable = false,insertable = true,updatable = true)
	private String roleName;
	@Builder
	public RoleBean(Instant createdAt, Instant updatedAt, Long codeRole, String codeUniqueRole, String roleName) {
		super(createdAt, updatedAt);
		this.codeRole = codeRole;
		this.codeUniqueRole = codeUniqueRole;
		this.roleName = roleName;
		 
	}
	 
	
	
	

}
