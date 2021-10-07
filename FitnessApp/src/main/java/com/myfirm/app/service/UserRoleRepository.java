package com.myfirm.app.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfirm.app.entity.UserRole;



@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	 UserRole findByName(String role);
}
