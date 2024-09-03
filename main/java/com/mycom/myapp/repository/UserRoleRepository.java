package com.mycom.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	UserRole findByRoleName(String var1);
}

