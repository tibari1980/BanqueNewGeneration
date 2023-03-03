package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.RibCompteBean;

@Repository
public interface RibCompteRepository extends JpaRepository<RibCompteBean, Long> {

}
