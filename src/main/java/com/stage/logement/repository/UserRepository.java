package com.stage.logement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.logement.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email); 


}
