package com.arcesi.banque.response;

/**
 * @author Mr zeroual tibari
 * ingénieur de develppement
 */
import java.time.Instant;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class EtablissementResponse extends AbstractEntityResponse {

	private static final long serialVersionUID = 1L;

	private String codeEtablissement;
	private String codeEtablissementUnique;
	private String codeGuiche;
	private String libelleEtablissement;
	private String emailEtablissement;
	private String telephoneEtablissement;
	private String faxEtablissement;
	private String domiciliation;
	private Collection<CompteResponse> compteResponses;

	@Builder
	public EtablissementResponse(Instant createdAt, Instant updatedAt, String codeEtablissement,
			String codeEtablissementUnique, String codeGuiche, String libelleEtablissement, String emailEtablissement,
			String telephoneEtablissement, String faxEtablissement, String domiciliation,
			Collection<CompteResponse> compteResponses) {
		super(createdAt, updatedAt);
		this.codeEtablissement = codeEtablissement;
		this.codeEtablissementUnique = codeEtablissementUnique;
		this.codeGuiche = codeGuiche;
		this.libelleEtablissement = libelleEtablissement;
		this.emailEtablissement = emailEtablissement;
		this.telephoneEtablissement = telephoneEtablissement;
		this.faxEtablissement = faxEtablissement;
		this.domiciliation = domiciliation;
		this.compteResponses = compteResponses;
	}

}
