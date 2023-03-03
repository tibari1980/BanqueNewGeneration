package com.arcesi.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.arcesi.banque.entites.UserBean;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserBean, Long> {

	Optional<UserBean> findByEmail(String email);

	UserBean  findUserBeanByEmail(String email);

}
