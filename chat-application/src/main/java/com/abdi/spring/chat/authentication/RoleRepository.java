package com.abdi.spring.chat.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);

}
