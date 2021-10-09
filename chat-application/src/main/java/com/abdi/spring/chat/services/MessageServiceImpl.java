package com.abdi.spring.chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdi.spring.chat.entities.ChatRoom;
import com.abdi.spring.chat.entities.Message;
import com.abdi.spring.chat.repositories.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	ChatRoomService chatRoomService;
	
	
	@Override
	public void appendInstantMessageToConversations(Message message) {
		if (message.isPublic()) {
			ChatRoom chatRoom = chatRoomService.findById(message.getChatRoomId());
			
			chatRoom.getConnectedUsers().forEach(connectedUser -> {
				message.setUsername(connectedUser.getUsername());
				messageRepository.save(message);
			});
			
		} else {
			message.setUsername(message.getFromUser());
			messageRepository.save(message);
			
			message.setUsername(message.getToUser());
			messageRepository.save(message);
		}
		
	}

	@Override
	public List<Message> findAllInstantMessagesFor(String username, String chatRoomId) {
		return messageRepository.findByUsernameAndChatRoomId(username, chatRoomId);
	}

}
