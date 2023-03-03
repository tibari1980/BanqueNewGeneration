package com.arcesi.banque.entites;

import java.time.Instant;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * @author Mr Zeroual Tibari
 *
 *Ingénieur de developpement
 */

@Entity
@Table(name = "ETABLISSEMENT", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_ETABLISSEMENT", name = "CODE_ETABLISSEMENT_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_ETABLISSEMENT_UNIQUE", name = "CODE_ETABLISSEMENT_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "EMAIL",name="EMAIL_ETABLISSEMENT_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_GUICHET",name="CODE_GUICHET_SEQUENCE")})



@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EtablissementBean extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CODE_ETABLISSEMENT", nullable = false, unique = true)
	private String codeEtablissement;
	@Column(name = "CODE_ETABLISSEMENT_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeEtablissementUnique;
	@Column(name="CODE_GUICHET",length = 5,unique = true,insertable = true,updatable = true,nullable = false)
	private String codeGuiche;
	@Column(name = "LIBELLE_ETABLISSEMENT")
	private String libelleEtablissement;
	@Column(name="EMAIL")
	private String emailEtablissement;
	@Column(name = "TELEPHONE")
	private String telephoneEtablissement;
	@Column(name = "FAXE")
	private String faxEtablissement;
	@Column(name="DOMICILIATION")
	private String domiciliation;
	
	@OneToMany(mappedBy = "etablissementBean")
	private Collection<CompteBean> compteBeans;

	@Builder
	public EtablissementBean(Instant createdAt, Instant updatedAt, String codeEtablissement,
			String codeEtablissementUnique, String codeGuiche, String libelleEtablissement, String emailEtablissement,
			String telephoneEtablissement, String faxEtablissement, String domiciliation,
			Collection<CompteBean> compteBeans) {
		super(createdAt, updatedAt);
		this.codeEtablissement = codeEtablissement;
		this.codeEtablissementUnique = codeEtablissementUnique;
		this.codeGuiche = codeGuiche;
		this.libelleEtablissement = libelleEtablissement;
		this.emailEtablissement = emailEtablissement;
		this.telephoneEtablissement = telephoneEtablissement;
		this.faxEtablissement = faxEtablissement;
		this.domiciliation = domiciliation;
		this.compteBeans = compteBeans;
	}

	 
	
	
	
}
