package com.arcesi.banque.entites;

import java.time.Instant;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.arcesi.banque.enums.CompteStatusEnumeration;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = "CC")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompteCourantBean extends CompteBean {

	private static final long serialVersionUID = 1L;
	@Column(name = "DECOUVERT")
	private double decouvert;
	@Builder
	public CompteCourantBean(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique,
			double soldeCompte, CompteStatusEnumeration statusCompte, ClientBean clientBean,
			Collection<OperationBean> operationBeans, EmployeBean employeBean, RibCompteBean ribCompteBean,
			CarteBancaireCompteBean carteBean, EtablissementBean etablissementBean, double decouvert) {
		super(createdAt, updatedAt, codeCompte, codeCompteUnique, soldeCompte, statusCompte, clientBean, operationBeans,
				employeBean, ribCompteBean, carteBean, etablissementBean);
		this.decouvert = decouvert;
	}
	 
	 
	
	
	
	
	 
	
	
}
