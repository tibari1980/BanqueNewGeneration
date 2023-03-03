package com.arcesi.banque.request;

import java.time.Instant;

import com.arcesi.banque.dto.CompteDTO;

import lombok.Builder;
/**
 * @author Mr zeroual tibari
 * ingénieur développement
 */
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter  @ToString
public class RibCompteRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 1L;
	private String etablissement; // 20041
	private String guichet; // 00001
	private String numeroCompte; // 6223900P020
	private String cleRib;
	private String domiciliationCompte;
	private String ibanCompte;
	private String bicCompte;
	private CompteDTO compteDTO;

	@Builder
	public RibCompteRequest(Instant createdAt, Instant updatedAt, String etablissement, String guichet,
			String numeroCompte, String cleRib, String domiciliationCompte, String ibanCompte, String bicCompte,
			CompteDTO compteDTO) {
		super(createdAt, updatedAt);
		this.etablissement = etablissement;
		this.guichet = guichet;
		this.numeroCompte = numeroCompte;
		this.cleRib = cleRib;
		this.domiciliationCompte = domiciliationCompte;
		this.ibanCompte = ibanCompte;
		this.bicCompte = bicCompte;
		this.compteDTO = compteDTO;
	}

}
