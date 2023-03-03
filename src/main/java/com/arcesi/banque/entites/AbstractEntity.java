package com.arcesi.banque.entites;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author Zeroual tibari Ingénieur développement
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter 
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@ToString
public class AbstractEntity implements Serializable {

 
	private static final long serialVersionUID = 1L;
	@CreatedDate
	@Column(name="DATE_CREATION",nullable = false,updatable = false,insertable = true)
	public Instant createdAt;
	@LastModifiedDate
	@Column(name="DATE_MODIFICATION",nullable = true,updatable = true,insertable = true)
	public Instant updatedAt; 
}
