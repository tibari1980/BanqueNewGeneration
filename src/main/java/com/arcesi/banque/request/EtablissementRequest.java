package com.arcesi.banque.request;
/**
 * @author Mr zeroual tibari
 * ingénieur développement
 */
import java.time.Instant;
import java.util.Collection;

import com.arcesi.banque.dto.CompteDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class EtablissementRequest extends AbstractEntityRequest {

	 
	private static final long serialVersionUID = 1L;
	private String codeGuiche;
	private String libelleEtablissement;
	private String emailEtablissement;
	private String telephoneEtablissement;
	private String faxEtablissement;
	private String domiciliation;
	private Collection<CompteDTO> compteDTOs;

	public EtablissementRequest(Instant createdAt, Instant updatedAt, String codeGuiche, String libelleEtablissement,
			String emailEtablissement, String telephoneEtablissement, String faxEtablissement, String domiciliation) {
		super(createdAt, updatedAt);
		this.codeGuiche = codeGuiche;
		this.libelleEtablissement = libelleEtablissement;
		this.emailEtablissement = emailEtablissement;
		this.telephoneEtablissement = telephoneEtablissement;
		this.faxEtablissement = faxEtablissement;
		this.domiciliation = domiciliation;
	}

}
