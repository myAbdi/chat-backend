package com.abdi.spring.chat.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdi.spring.chat.entities.ChatRoom;
import com.abdi.spring.chat.entities.ChatRoomUser;
import com.abdi.spring.chat.entities.Message;
import com.abdi.spring.chat.repositories.ChatRoomRepository;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	@Autowired
	MessageService messageService;
	
	
	@Override
	public ChatRoom save(ChatRoom chatRoom) {
		return chatRoomRepository.save(chatRoom);
	}
	public void delete(ChatRoom chatRoom) {
		chatRoomRepository.delete(chatRoom);
	}
	@Override
	public Optional<ChatRoom> findById(String id) {
		return chatRoomRepository.findById(id);
	}
	

	@Override
	public ChatRoom join(ChatRoomUser joiningUser, ChatRoom chatRoom) {
			List<ChatRoomUser> users = chatRoom.getConnectedUsers();
			users.add(joiningUser);
			chatRoom.setConnectedUsers(users);
			chatRoomRepository.save(chatRoom);
			
			// send well come message
			
			//Update connected users list via websocket
			
			return chatRoom;
			}

	@Override
	public ChatRoom leave(ChatRoomUser leavingUser, ChatRoom chatRoom) {
		List<ChatRoomUser> users = chatRoom.getConnectedUsers();
		users.remove(leavingUser);
		chatRoom.setConnectedUsers(users);
		chatRoomRepository.save(chatRoom);
		return chatRoom;
	}

	@Override
	public Iterable<ChatRoom> findAll() {
		return chatRoomRepository.findAll();
	}

	@Override
	public void sendPublicMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendPrivateMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

}
