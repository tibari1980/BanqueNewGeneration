package com.arcesi.banque.entites;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.arcesi.banque.enums.TypeOperationEnumeration;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OPERATION", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_OPERATION", name = "CODE_OPERATION_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_OPERATION_UNIQUE", name = "CODE_OPERATION_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "operation_sequence", name = "operation_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_sequence")
	@Column(name = "CODE_OPERATION", nullable = false, unique = true)
	private Long codeOperation;
	@Column(name = "CODE_OPERATION_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeUniqueOperation;
	@Column(name = "MONTANT_OPERATION")
	private double montantOperation;
	@Column(name = "LIBELLE_OPERATION")
	private String libelleOperation;
	@Enumerated(EnumType.STRING)
	private TypeOperationEnumeration typeOperation;
	@ManyToOne
	private CompteBean compteBean;
	@ManyToOne
	private EmployeBean employeBean;

	@Builder
	public OperationBean(Instant createdAt, Instant updatedAt, Long codeOperation, String codeUniqueOperation,
			double montantOperation, String libelleOperation, TypeOperationEnumeration typeOperation,
			CompteBean compteBean, EmployeBean employeBean) {
		super(createdAt, updatedAt);
		this.codeOperation = codeOperation;
		this.codeUniqueOperation = codeUniqueOperation;
		this.montantOperation = montantOperation;
		this.libelleOperation = libelleOperation;
		this.typeOperation = typeOperation;
		this.compteBean = compteBean;
		this.employeBean = employeBean;
	}

}
