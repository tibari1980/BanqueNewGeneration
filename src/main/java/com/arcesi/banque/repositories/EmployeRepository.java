package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.EmployeBean;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeBean, Long>{

}
