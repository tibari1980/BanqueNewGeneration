package com.arcesi.banque.request;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AbstractEntityRequest implements Serializable {

	 
	private static final long serialVersionUID = 1L;
	public Instant createdAt;
	public Instant updatedAt;
	
	public AbstractEntityRequest(Instant createdAt, Instant updatedAt) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
}
