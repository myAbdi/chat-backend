package com.abdi.spring.chat.authentication;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
	 @Id	 
	 private String username;
	 private String password;
	 private String name;
	 private String email;
}
