package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.CompteBean;

@Repository
public interface CompteRepository  extends JpaRepository<CompteBean, Long>{

}
