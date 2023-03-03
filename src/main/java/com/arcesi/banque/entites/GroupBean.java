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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

/**
 * 
 * @author Zeroual tibari Ingénieur de développement
 *
 */
@Entity
@Table(name = "GROUPE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_GROUPE", name = "CODE_GROUPE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_GROUPE_UNIQUE", name = "CODE_GROUPE_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "LIBELLE", name = "LIBELLE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "groupe_sequence", name = "groupe_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupe_sequence")
	@Column(name = "CODE_GROUPE", nullable = false, unique = true)
	private Long codeGroupe;
	@Column(name = "CODE_GROUPE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeGroupUnique;
	@Column(name = "LIBELLE", unique = true)
	private String libelleGroup;

	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUPES_EMPLOYES", joinColumns = {
			@JoinColumn(name = "CODE_GROUPES")}, inverseJoinColumns = {
					@JoinColumn(name = "CODE_EMPLOYES")})
	private Set<EmployeBean> employeBeans = new HashSet<EmployeBean>();

	@Builder
	public GroupBean(Instant createdAt, Instant updatedAt, Long codeGroupe, String codeGroupUnique, String libelleGroup,
			Set<EmployeBean> employeBeans) {
		super(createdAt, updatedAt);
		this.codeGroupe = codeGroupe;
		this.codeGroupUnique = codeGroupUnique;
		this.libelleGroup = libelleGroup;
		this.employeBeans = new HashSet<EmployeBean>();
	}

	

}
