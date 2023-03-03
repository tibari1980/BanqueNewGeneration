package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.banque.entites.GroupBean;

@Repository
public interface GroupRepository  extends JpaRepository<GroupBean, Long>{

}
