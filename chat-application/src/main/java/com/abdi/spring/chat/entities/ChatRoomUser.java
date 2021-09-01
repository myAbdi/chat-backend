package com.abdi.spring.chat.entities;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ChatRoomUser {
	
	private String username;
	private Date joinedAt = new Date();
	
	public ChatRoomUser(String username) {
		super();
		this.username = username;
	}

	
}
