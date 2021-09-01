package com.abdi.spring.chat.services;


import java.util.Optional;

import com.abdi.spring.chat.entities.ChatRoom;
import com.abdi.spring.chat.entities.ChatRoomUser;
import com.abdi.spring.chat.entities.Message;


public interface ChatRoomService {
	
	
	ChatRoom save(ChatRoom chatRoom);
	void delete(ChatRoom chatRoom);
	Optional<ChatRoom> findById(String id);
	ChatRoom join(ChatRoomUser joiningUser, ChatRoom chatRoom);
	ChatRoom leave(ChatRoomUser leavingUser, ChatRoom chatRoom);
	Iterable<ChatRoom> findAll();
	void sendPublicMessage(Message message);
	void sendPrivateMessage(Message message);
}
