package com.arcesi.banque.entites;

import java.time.Instant;
import java.time.LocalDate;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.arcesi.banque.enums.StatusCarteEnum;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CARTE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_CARTE", name = "CODE_CARTE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_CARTE_UNIQUE", name = "CODE_CARTE_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CarteBancaireCompteBean extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "carte_sequence", name = "carte_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carte_sequence")
	@Column(name = "CODE_CARTE", nullable = false, unique = true)
	private Long codeCarte;
	@Column(name = "CODE_CARTE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeCarteUnique;
	@Column(name = "NUMERO_CARTE", length = 21, unique = true, nullable = false, insertable = true)
	private String numeroCarte;
	@Column(name = "DATE_EXPERATION", nullable = false, insertable = true, updatable = true)
	private LocalDate dateExperation;
	@Column(name = "CODE_VERIFICATION", length = 3, nullable = false, updatable = true, insertable = true)
	private String codeVerification;
	@Column(name = "STATUS_CARTE_BANCAIRE")
	@Enumerated(EnumType.STRING)
	private StatusCarteEnum statusCarte;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_compte_carte_bancaire_id", nullable = false)
	private CompteBean compteBean;

	@Builder
	public CarteBancaireCompteBean(Instant createdAt, Instant updatedAt, Long codeCarte, String codeCarteUnique,
			String numeroCarte, LocalDate dateExperation, String codeVerification, StatusCarteEnum statusCarte,
			CompteBean compteBean) {
		super(createdAt, updatedAt);
		this.codeCarte = codeCarte;
		this.codeCarteUnique = codeCarteUnique;
		this.numeroCarte = numeroCarte;
		this.dateExperation = dateExperation;
		this.codeVerification = codeVerification;
		this.statusCarte = statusCarte;
		this.compteBean = compteBean;
	}

}
