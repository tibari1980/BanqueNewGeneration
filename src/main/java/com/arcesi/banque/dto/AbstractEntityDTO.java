package com.arcesi.banque.dto;

/**
 * @author Mr zeroua ltibari
 * Ingénieur développement
 */
import java.io.Serializable;
import java.time.Instant;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class AbstractEntityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	public Instant createdAt;
	public Instant updatedAt;

	public AbstractEntityDTO(Instant createdAt, Instant updatedAt) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
