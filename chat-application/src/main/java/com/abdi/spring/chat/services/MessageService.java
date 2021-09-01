package com.abdi.spring.chat.services;

import java.util.List;

import com.abdi.spring.chat.entities.Message;

public interface MessageService {

	void appendInstantMessageToConversations(Message message);
	List<Message> findAllInstantMessagesFor(String username, String chatRoomId);
}
