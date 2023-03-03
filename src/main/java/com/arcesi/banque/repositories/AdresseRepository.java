package com.arcesi.banque.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Mr Zeroual tibari
 * ingénieur developpement
 */
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.AdresseBean;

@Repository
public interface AdresseRepository extends JpaRepository<AdresseBean, Long> {

}
