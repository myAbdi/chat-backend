package com.abdi.spring.chat.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("chatroom")
public class ChatRoom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	 private String id;
	 private String name;
	 private String description;
	 private List<ChatRoomUser> connectedUsers = new ArrayList<>();

}
