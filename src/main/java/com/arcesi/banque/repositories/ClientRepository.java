package com.arcesi.banque.repositories;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.ClientBean;

@Repository
public interface ClientRepository extends JpaRepository<ClientBean, Long> {
	ClientBean findClientBeanByCode(Long code);

	ClientBean findByCodeClientUnique(String code);

	ClientBean findClientBeanByEmailClientIgnoreCase(String email);

	ClientBean findClientBeanByNomClient(String nom);

	ClientBean findClientBeanByNomClientAndPrenomClientAndDateNaissanceClient(String nom, String prenom,
			LocalDate dateOfBird);
}
