package com.arcesi.banque.entites;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONFIRMATION_TOKEN")
public class ConfirmationToken {

	@SequenceGenerator(allocationSize = 1, name = "confirmation_sequence", sequenceName = "confirmation_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_sequence")
	private Long id;
	@Column(nullable = false, name = "TOKEN")
	private String token;
	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "EXPERID_AT", nullable = false)
	private LocalDateTime experidAt;
	private LocalDateTime confirmedAt;
	@ManyToOne
	@JoinColumn(nullable = false, name = "app_user_id")
	private UserBean appUser;

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime experidAt, UserBean appUser) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.experidAt = experidAt;
		this.appUser = appUser;
	}

}
