package com.beebrick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beebrick.entity.UserLogin;



@Repository
public interface UserRepositoryLogin extends JpaRepository<UserLogin, Long>{
	UserLogin findByEmail(String email);
}
