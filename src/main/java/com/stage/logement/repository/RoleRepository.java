package com.stage.logement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.logement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}