package com.arcesi.banque.entites;

import java.time.Instant;
import java.util.ArrayList;
/**
 * @author Mr Zeroual Tibari 
 * ingénieur developpement
 */
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "COMPTE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_COMPTE", name = "CODE_COMPTE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_COMPTE_UNIQUE", name = "CODE_COMPTE_UNIQUE_SEQUENCE") })

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public  class CompteBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "compte_sequence", name = "compte_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compte_sequence")
	@Column(name = "CODE_COMPTE", nullable = false, unique = true)
	private Long codeCompte;
	@Column(name = "CODE_COMPTE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeCompteUnique;
	@Column(name = "SOLDE_COMPTE")
	private double soldeCompte;
	@Enumerated(EnumType.STRING)
	private CompteStatusEnumeration statusCompte;
	
	@ManyToOne
	private ClientBean clientBean;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="FK_COMPTE_OPERATION",referencedColumnName = "CODE_COMPTE")
	private Collection<OperationBean> operationBeans;
	
	@ManyToOne
	private EmployeBean employeBean;

	 @OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "compteBean")
	private RibCompteBean ribCompteBean=null;
	
	 @OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "compteBean")
	public CarteBancaireCompteBean carteBean=null;
	
	@ManyToOne
	private EtablissementBean etablissementBean=null;

	public CompteBean(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique,
			double soldeCompte, CompteStatusEnumeration statusCompte, ClientBean clientBean,
			Collection<OperationBean> operationBeans, EmployeBean employeBean, RibCompteBean ribCompteBean,
			CarteBancaireCompteBean carteBean, EtablissementBean etablissementBean) {
		super(createdAt, updatedAt);
		this.codeCompte = codeCompte;
		this.codeCompteUnique = codeCompteUnique;
		this.soldeCompte = soldeCompte;
		this.statusCompte = statusCompte;
		this.clientBean = clientBean;
		this.operationBeans =new ArrayList<OperationBean>();
		this.employeBean = employeBean;
		this.ribCompteBean = ribCompteBean;
		this.carteBean = carteBean;
		this.etablissementBean = etablissementBean;
	}

	

	
	
	
	 
	
}
