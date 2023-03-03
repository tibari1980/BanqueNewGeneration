package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.EtablissementBean;

@Repository
public interface EtablissementRepository extends JpaRepository<EtablissementBean, String> {

}
