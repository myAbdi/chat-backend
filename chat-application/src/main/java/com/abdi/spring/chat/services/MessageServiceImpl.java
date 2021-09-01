package com.abdi.spring.chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdi.spring.chat.entities.Message;
import com.abdi.spring.chat.repositories.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	
	@Override
	public void appendInstantMessageToConversations(Message message) {

		
	}

	@Override
	public List<Message> findAllInstantMessagesFor(String username, String chatRoomId) {
		// TODO Auto-generated method stub
		return null;
	}

}
