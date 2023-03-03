package com.arcesi.banque.entites;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.arcesi.banque.enums.SexEnumeration;
import com.arcesi.banque.enums.TypeClientEnumeration;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CLIENT", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_CLIENT", name = "CODE_CLIENT_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_CLIENT_UNIQUE", name = "CODE_CLIENT_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "client_sequence", name = "client_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
	@Column(name = "CODE_CLIENT", nullable = false, unique = true)
	private Long code;
	@Column(name = "CODE_CLIENT_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeClientUnique;
	@Column(name = "TYPE_CLIENT",nullable = false,insertable = true,updatable = true)
	@Enumerated(EnumType.STRING)
	private TypeClientEnumeration typeClient;
	@Column(name = "SEX",nullable = false,insertable = true,updatable = true)
	@Enumerated(EnumType.STRING)
	private SexEnumeration sex;
	@Column(name = "NOM", length = 40, insertable = true, updatable = true,nullable = false)
	private String nomClient;
	@Column(name = "PRENOM", length = 40, insertable = true, updatable = true,nullable = false)
	private String prenomClient;
	@Column(name = "EMAIL", length = 100, nullable = false, unique = true, insertable = true, updatable = true)
	private String emailClient;
	@Column(name = "TELEPHONE")
	private String telephoneClient;
	@Column(name = "PHOTO")
	private String photoClient;
	@Column(name = "DATE_NAISSANCE",nullable = false,insertable = true,updatable = true)
	private LocalDate dateNaissanceClient;
	@Column(name = "AGE")
	private Integer ageClient;
	@Column(name = "PAYS_NAISSANCE",insertable = true,updatable = true,nullable = false)
	private String paysNaissance;
	@Column(name = "VILLE_NAISSANCE",insertable = true,updatable = true,nullable = false)
	private String villeNaissance;
	@Column(name = "DEPARTEMENT_NAISSANCE", length = 3)
	private String departementNaissance;
	@Column(name = "RAISON_SOCIAL", length = 60)
	private String raisonSocial;
	@Column(name = "SIRET", length = 50)
	private String numeroSiret;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="FK_CLIENT_COMPTE",referencedColumnName = "CODE_CLIENT")
	private Collection<CompteBean> compteBeans;

	 @OneToMany( cascade = CascadeType.ALL)
	 @JoinColumn(name = "FK_CLIENT_ADRESSE",referencedColumnName = "CODE_CLIENT")   
	private List<AdresseBean> adresseBeans;
	
	
	@Builder
	public ClientBean(Instant createdAt, Instant updatedAt, Long code, String codeClientUnique,
			TypeClientEnumeration typeClient, SexEnumeration sex, String nomClient, String prenomClient,
			String emailClient, String telephoneClient, String photoClient, LocalDate dateNaissanceClient,
			Integer ageClient, String paysNaissance, String villeNaissance, String departementNaissance,
			String raisonSocial, String numeroSiret, Collection<CompteBean> compteBeans,
			List<AdresseBean> adresseBeans) {
		super(createdAt, updatedAt);
		this.code = code;
		this.codeClientUnique = codeClientUnique;
		this.typeClient = typeClient;
		this.sex = sex;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.telephoneClient = telephoneClient;
		this.photoClient = photoClient;
		this.dateNaissanceClient = dateNaissanceClient;
		this.ageClient = ageClient;
		this.paysNaissance = paysNaissance;
		this.villeNaissance = villeNaissance;
		this.departementNaissance = departementNaissance;
		this.raisonSocial = raisonSocial;
		this.numeroSiret = numeroSiret;
		 
		this.compteBeans=new ArrayList<CompteBean>();
		this.adresseBeans =new ArrayList<AdresseBean>();
	}

	
}
