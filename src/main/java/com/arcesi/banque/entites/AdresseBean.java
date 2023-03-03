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

import com.arcesi.banque.enums.AdresseTypeEnum;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ADRESSE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_ADRESSE", name = "CODE_ADRESSE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_ADRESSE_UNIQUE", name = "CODE_ADRESSE_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdresseBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "adresse_sequence", name = "adresse_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresse_sequence")
	@Column(name = "CODE_ADRESSE", nullable = false, unique = true)
	private Long codeAdresse;
	@Column(name = "CODE_ADRESSE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeUniqueAdresse;
	@Column(name = "NUMERO_RUE", nullable = false, updatable = true, insertable = true)
	private Integer numeroRue;
	@Column(name = "NOM_RUE", nullable = false, updatable = true, insertable = true, length = 40)
	private String nomRue;
	@Column(name = "CODE_POSTALE", insertable = true, updatable = true, length = 5)
	private String codePostale;
	@Column(name = "VILLE", length = 40)
	private String ville;
	@Column(name = "PAYS", insertable = true, updatable = true, length = 40)
	private String pays;
	@Column(name = "TYPE_ADRESSE")
	@Enumerated(EnumType.STRING)
	private AdresseTypeEnum typeAdresse;
	
	@ManyToOne
	private ClientBean clientBean;
	@ManyToOne
	private EmployeBean employeBean;
	
	@Builder
	public AdresseBean(Instant createdAt, Instant updatedAt, Long codeAdresse, String codeUniqueAdresse,
			Integer numeroRue, String nomRue, String codePostale, String ville, String pays,
			AdresseTypeEnum typeAdresse, ClientBean clientBean, EmployeBean employeBean) {
		super(createdAt, updatedAt);
		this.codeAdresse = codeAdresse;
		this.codeUniqueAdresse = codeUniqueAdresse;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostale = codePostale;
		this.ville = ville;
		this.pays = pays;
		this.typeAdresse = typeAdresse;
		this.clientBean = clientBean;
		this.employeBean = employeBean;
	}

	

}
