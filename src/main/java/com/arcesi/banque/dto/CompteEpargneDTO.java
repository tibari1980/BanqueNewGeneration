package com.arcesi.banque.dto;

/**
 * @author mr zeroual tibari
 * ingénieur de développement
 */
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.enums.CompteStatusEnumeration;
import com.arcesi.banque.exceptions.InvalidEntityException;
import com.arcesi.banque.request.CompteEpargneRequest;
import com.arcesi.banque.response.CompteEpargneResponse;
import com.arcesi.banque.response.CompteResponse;
import com.arcesi.banque.response.OperationResponse;
import com.arcesi.banque.response.RibCompteResponse;

import lombok.Builder;
/**
 * @author Mr zeroual tibari
 * Ingénieur de developpement
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompteEpargneDTO extends CompteDTO {

	private static final long serialVersionUID = 1L;
	private double tauxInteret;

	@Builder
	public CompteEpargneDTO(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique,
			double soldeCompte, CompteStatusEnumeration statusCompte, ClientDTO clientDTO,
			Collection<OperationDTO> operationDTOs, EmployeDTO employeDTO, RibCompteDTO ribCompteDTO,
			CarteBancaireCompteDTO carteDTO, EtablissementDTO etablissementDTO, double tauxInteret) {
		super(createdAt, updatedAt, codeCompte, codeCompteUnique, soldeCompte, statusCompte, clientDTO, operationDTOs,
				employeDTO, ribCompteDTO, carteDTO, etablissementDTO);
		this.tauxInteret = tauxInteret;
	}
	/**
	 * 
	 * @param bean {@link CompteEpargneBean }
	 * @return {@link CompteCourantDTO}
	 */
	public static CompteEpargneDTO toEntity(CompteEpargneBean bean) {
		if(null==bean) {
			throw new InvalidEntityException("L'objet n'est pas valide ");
		}
		CompteEpargneDTO ce= CompteEpargneDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeCompte(bean.getCodeCompte())
				.codeCompteUnique(bean.getCodeCompteUnique())
				.statusCompte(bean.getStatusCompte())
				.soldeCompte(bean.getSoldeCompte())
				.tauxInteret(bean.getTauxInteret())
				.ribCompteDTO(
						bean.getRibCompteBean()!=null? RibCompteDTO.toEntity(bean.getRibCompteBean()):null
						)
				.carteDTO(
						bean.getCarteBean()!=null? CarteBancaireCompteDTO.toEntity(bean.getCarteBean()):null
						)
				.operationDTOs(
						bean.getOperationBeans()!=null?
								bean.getOperationBeans().stream().map(OperationDTO::toEntity).collect(Collectors.toList()):null
						)
				.build();
		
		return ce;
	}
	
	/**
	 * Méthod permettant de mapper le CompteEpagneDto to CompteEpargneBean
	 * @param dto {@link CompteCourantDTO }
	 * @return {@link CompteEpargneBean}
	 */
	public static CompteEpargneBean fromEntity(CompteEpargneDTO dto) {
		if(null==dto) {
			return null;
		}
		return CompteEpargneBean.builder()
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.codeCompte(dto.getCodeCompte())
				.codeCompteUnique(dto.getCodeCompteUnique())
				.statusCompte(dto.getStatusCompte())
				.soldeCompte(dto.getSoldeCompte())
				.build();
	}
	public static CompteResponse ResponseCompte(CompteEpargneDTO dto) {
		if(null==dto) {
			return null;
		}
		 CompteEpargneResponse response= CompteEpargneResponse.builder()
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.codeCompte(dto.getCodeCompte())
				.codeCompteUnique(dto.getCodeCompteUnique())
				.statusCompte(dto.getStatusCompte())
				.soldeCompte(dto.getSoldeCompte())
				.carterBancaireCompteResponse(CarteBancaireCompteDTO.toResponse(dto.getCarteDTO()))
				.ribCompteResponse(RibCompteResponse.toResponse(dto.getRibCompteDTO()))
				.build();
		 if(CollectionUtils.isNotEmpty(dto.getOperationDTOs())) {
				List<OperationResponse> operationResponses=new ArrayList<OperationResponse>();
				for(OperationDTO d:dto.getOperationDTOs()) {
					operationResponses.add(OperationDTO.toResponse(d));
				}
				if(CollectionUtils.isNotEmpty(operationResponses)) {
					response.setOperationResponses(operationResponses);
				}
			}
		 
		 return response;
		
		
	}
	public static CompteEpargneDTO compteRequestToCompteDTO(CompteEpargneRequest bean) {
		if(null==bean) {
			return null;
		}
		return CompteEpargneDTO.builder()
				.statusCompte(bean.getStatusCompte())
				.soldeCompte(bean.getSoldeCompte())
				.tauxInteret(bean.getTauxInteret())
				.build();
	}

}
