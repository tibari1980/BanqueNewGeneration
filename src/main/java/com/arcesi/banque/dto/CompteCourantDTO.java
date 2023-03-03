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

import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.enums.CompteStatusEnumeration;
import com.arcesi.banque.request.CompteCourantRequest;
import com.arcesi.banque.response.CompteCourantResponse;
import com.arcesi.banque.response.OperationResponse;
import com.arcesi.banque.response.RibCompteResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompteCourantDTO extends CompteDTO {

	private static final long serialVersionUID = 1L;
	private double decouvert;

	 @Builder
		public CompteCourantDTO(Instant createdAt, Instant updatedAt, Long codeCompte, String codeCompteUnique,
				double soldeCompte, CompteStatusEnumeration statusCompte, ClientDTO clientDTO,
				Collection<OperationDTO> operationDTOs, EmployeDTO employeDTO, RibCompteDTO ribCompteDTO,
				CarteBancaireCompteDTO carteDTO, EtablissementDTO etablissementDTO, double decouvert) {
			super(createdAt, updatedAt, codeCompte, codeCompteUnique, soldeCompte, statusCompte, clientDTO, operationDTOs,
					employeDTO, ribCompteDTO, carteDTO, etablissementDTO);
			this.decouvert = decouvert;
		}
	public static CompteCourantDTO toEntity(CompteCourantBean bean) {
		if(null==bean) {
			return null;
		}
		CompteCourantDTO cc= CompteCourantDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.codeCompte(bean.getCodeCompte())
				.codeCompteUnique(bean.getCodeCompteUnique())
				.soldeCompte(bean.getSoldeCompte())
				.statusCompte(bean.getStatusCompte())
				.decouvert(bean.getDecouvert())
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
		return cc;
	}
	
	
	public static CompteCourantBean fromEntity(CompteCourantDTO dto) {
		if(null==dto) {
			return null;
		}
		return CompteCourantBean.builder()
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.codeCompte(dto.getCodeCompte())
				.codeCompteUnique(dto.getCodeCompteUnique())
				.soldeCompte(dto.getSoldeCompte())
				.decouvert(dto.getDecouvert())
				.build();
	}


	public static CompteCourantResponse toResponseCompte(CompteCourantDTO dto) {
		if(null==dto) {
			return null;
		}
		CompteCourantResponse response= CompteCourantResponse.builder()
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.codeCompte(dto.getCodeCompte())
				.codeCompteUnique(dto.getCodeCompteUnique())
				.soldeCompte(dto.getSoldeCompte())
				.decouvert(dto.getDecouvert())
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


	public static CompteCourantDTO compteRequestToCompteDTO(CompteCourantRequest bean) {
		if(null==bean) {
			return null;
		}
		return CompteCourantDTO.builder()
				.soldeCompte(bean.getSoldeCompte())
				.statusCompte(bean.getStatusCompte())
				.decouvert(bean.getDecouvert())
				.build();
	}

   


	
}
