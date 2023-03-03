package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.CarteBancaireCompteBean;

@Repository
public interface CarteBancaireCompteRepository extends JpaRepository<CarteBancaireCompteBean, Long> {

}
