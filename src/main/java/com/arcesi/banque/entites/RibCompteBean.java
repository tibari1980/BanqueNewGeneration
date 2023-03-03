package com.arcesi.banque.entites;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "RIBCOMPTE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_RIB", name = "CODE_RIB_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_RIB_UNIQUE", name = "CODE_RIB_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RibCompteBean extends AbstractEntity {

	// RIB - Identifiant national de compte
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "rib_sequence", name = "rib_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rib_sequence")
	@Column(name = "CODE_RIB", nullable = false, unique = true)
	private Long codeRib;
	@Column(name = "CODE_RIB_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeRibUnique;
	@Column(name = "ETABLISSEMENT", length = 5)
	private String etablissement; // 20041
	@Column(name = "GUICHET", length = 5)
	private String guichet; // 00001
	@Column(name = "NUMERO_COMPTE", unique = true, length = 11)
	private String numeroCompte; // 6223900P020
	@Column(name = "CLE_RIB", length = 2)
	private String cleRib;
	@Column(name = "DOMICILIATION_COMPTE")
	private String domiciliationCompte;
	@Column(name = "IBAN_COMPTE", length = 27)
	private String ibanCompte;
	@Column(name = "BIC_COMPTE", length = 11)
	private String bicCompte;

	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_compte_rib_id", nullable = false)
	private CompteBean compteBean = null;

	@Builder
	public RibCompteBean(Instant createdAt, Instant updatedAt, Long codeRib, String codeRibUnique, String etablissement,
			String guichet, String numeroCompte, String cleRib, String domiciliationCompte, String ibanCompte,
			String bicCompte, CompteBean compteBean) {
		super(createdAt, updatedAt);
		this.codeRib = codeRib;
		this.codeRibUnique = codeRibUnique;
		this.etablissement = etablissement;
		this.guichet = guichet;
		this.numeroCompte = numeroCompte;
		this.cleRib = cleRib;
		this.domiciliationCompte = domiciliationCompte;
		this.ibanCompte = ibanCompte;
		this.bicCompte = bicCompte;
		this.compteBean = compteBean;
	}

}
