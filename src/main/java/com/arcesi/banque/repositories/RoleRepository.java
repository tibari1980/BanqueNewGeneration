package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.banque.entites.RoleBean;
import com.arcesi.banque.enums.AppUserRole;

@Repository
public interface RoleRepository extends JpaRepository<RoleBean, Long> {

	RoleBean findRoleBeanByRoleName(String roleName);

}
