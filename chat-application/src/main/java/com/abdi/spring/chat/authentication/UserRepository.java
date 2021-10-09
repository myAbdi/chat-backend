package com.abdi.spring.chat.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


	 //User findByEmail(String email);

	//boolean exists(String username);

	User findByUsername(String username);

}
