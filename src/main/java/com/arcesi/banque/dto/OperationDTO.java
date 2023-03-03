package com.arcesi.banque.dto;
/**
 * @author Zeroual tibari
 * ingénieur développement
 */
import java.time.Instant;

import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.entites.OperationBean;
import com.arcesi.banque.enums.TypeOperationEnumeration;
import com.arcesi.banque.response.OperationResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OperationDTO extends AbstractEntityDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codeOperation;
	private String codeUniqueOperation;
	private double montantOperation;
	private String libelleOperation;
	private TypeOperationEnumeration typeOperation;
	private CompteDTO compteDTO;
	private EmployeDTO employeDTO;

	@Builder
	public OperationDTO(Instant createdAt, Instant updatedAt, Long codeOperation, String codeUniqueOperation,
			double montantOperation, String libelleOperation, TypeOperationEnumeration typeOperation,
			CompteDTO compteDTO, EmployeDTO employeDTO) {
		super(createdAt, updatedAt);
		this.codeOperation = codeOperation;
		this.codeUniqueOperation = codeUniqueOperation;
		this.montantOperation = montantOperation;
		this.libelleOperation = libelleOperation;
		this.typeOperation = typeOperation;
		this.compteDTO = compteDTO;
		this.employeDTO = employeDTO;
	}

	/**
	 * Méthode permettant de mapper un objet OperationBean to OperationDTO
	 * @param bean {@link OperationBean}
	 * @return bean {@link OperationDTO }
	 */
	public static OperationDTO toEntity(OperationBean bean) {
		if (null == bean) {
			return null;
		}
		OperationDTO dto = OperationDTO.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.libelleOperation(bean.getLibelleOperation())
				.codeOperation(bean.getCodeOperation())
				.codeUniqueOperation(bean.getCodeUniqueOperation())
				.employeDTO(EmployeDTO.toEntity(bean.getEmployeBean()))
				.montantOperation(bean.getMontantOperation())
				.typeOperation(bean.getTypeOperation()).build();
		 
		return dto;
	}

	/**
	 * Méthode permettant de mapper un objet OperationDTO to OperationBean
	 * @param bean {@link OperationDTO}
	 * @return bean {@link OperationBean }
	 */
	public static OperationBean fromEntity(OperationDTO bean) {
		if (null == bean) {
			return null;
		}
		OperationBean dto = OperationBean.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.libelleOperation(bean.getLibelleOperation())
				.codeOperation(bean.getCodeOperation())
				.codeUniqueOperation(bean.getCodeUniqueOperation())
				.employeBean(EmployeDTO.fromEntity(bean.getEmployeDTO()))
				.montantOperation(bean.getMontantOperation())
				.typeOperation(bean.getTypeOperation()).build();
		 
		return dto;
	}
	
	public static OperationResponse toResponse(OperationDTO bean) {
		if (null == bean) {
			return null;
		}
		OperationResponse dto = OperationResponse.builder()
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.libelleOperation(bean.getLibelleOperation())
				.codeOperation(bean.getCodeOperation())
				.codeUniqueOperation(bean.getCodeUniqueOperation())
				.montantOperation(bean.getMontantOperation())
				.employeResponse(EmployeDTO.toEmployeResponse(bean.getEmployeDTO()))
				.typeOperation(bean.getTypeOperation()).build();
		 
		return dto;
	}
}
