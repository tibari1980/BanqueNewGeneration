package com.arcesi.banque.response;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public abstract class AbstractEntityResponse implements Serializable {

	 
	private static final long serialVersionUID = 1L;
	public Instant createdAt;
	public Instant updatedAt;
	public AbstractEntityResponse(Instant createdAt, Instant updatedAt) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
}
