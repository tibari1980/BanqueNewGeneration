package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.OperationBean;

@Repository
public interface OperationRepository extends JpaRepository<OperationBean, Long> {

}
