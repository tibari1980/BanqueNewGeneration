package com.arcesi.banque.entites;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLOYE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_EMPLOYE", name = "CODE_EMPLOYE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_EMPLOYE_UNIQUE", name = "CODE_EMPLOYE_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "EMAIL", name = "EMAIL_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class EmployeBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "employe_sequence", name = "employe_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_sequence")
	@Column(name = "CODE_EMPLOYE", nullable = false, unique = true)
	private Long code;
	@Column(name = "CODE_EMPLOYE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeEmployeUnique;
	@Column(name = "NOM", length = 40, insertable = true, updatable = true)
	private String nomEmploye;
	@Column(name = "PRENOM", length = 40, insertable = true, updatable = true)
	private String prenomEmploye;
	@Column(name = "EMAIL", length = 100, nullable = false, unique = true, insertable = true, updatable = true)
	private String emailEmploye;
	@Column(name = "TELEPHONE")
	private String telephoneEmploye;
	@Column(name = "PHOTO")
	private String photoEmploye;
	@Column(name = "DATE_NAISSANCE")
	private LocalDate dateNaissanceEmploye;
	@Column(name = "AGE")
	private Integer ageEmploye;
	@Column(name = "PAYS_NAISSANCE")
	private String paysNaissance;
	@Column(name = "VILLE_NAISSANCE")
	private String villeNaissance;
	@Column(name = "DEPARTEMENT_NAISSANCE", length = 3)
	private String departementNaissance;
	@OneToMany(mappedBy = "employeBean", cascade = CascadeType.ALL)
	private Collection<CompteBean> compteBeans;
	@OneToMany(mappedBy = "employeBean", cascade = CascadeType.ALL)
	private Collection<AdresseBean> adresseBeans;
	@ManyToOne
	@JoinColumn(name = "code_employe_sup")
	private EmployeBean employeBeanSup;

	 
	@ManyToMany(mappedBy = "employeBeans", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<GroupBean> groupBeans = new HashSet<>();


	@Builder
	public EmployeBean(Instant createdAt, Instant updatedAt, Long code, String codeEmployeUnique, String nomEmploye,
			String prenomEmploye, String emailEmploye, String telephoneEmploye, String photoEmploye,
			LocalDate dateNaissanceEmploye, Integer ageEmploye, String paysNaissance, String villeNaissance,
			String departementNaissance, Collection<CompteBean> compteBeans, Collection<AdresseBean> adresseBeans, EmployeBean employeBeanSup, Set<GroupBean> groupBeans) {
		super(createdAt, updatedAt);
		this.code = code;
		this.codeEmployeUnique = codeEmployeUnique;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.emailEmploye = emailEmploye;
		this.telephoneEmploye = telephoneEmploye;
		this.photoEmploye = photoEmploye;
		this.dateNaissanceEmploye = dateNaissanceEmploye;
		this.ageEmploye = ageEmploye;
		this.paysNaissance = paysNaissance;
		this.villeNaissance = villeNaissance;
		this.departementNaissance = departementNaissance;
		this.compteBeans = compteBeans;
		this.adresseBeans = new ArrayList<AdresseBean>();
		this.employeBeanSup = employeBeanSup;
		
		//initialiser la liste des groupBean fixer nullPointerException
		this.groupBeans = new HashSet<GroupBean>();
	}

	 
	
}
