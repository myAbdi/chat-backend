package com.abdi.spring.chat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.abdi.spring.chat.entities.ChatRoom;
import com.abdi.spring.chat.entities.ChatRoomUser;
import com.abdi.spring.chat.entities.Message;
import com.abdi.spring.chat.repositories.ChatRoomRepository;
import com.abdi.spring.chat.util.Destinations;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	// reduce redundancy

	@Autowired
	private SimpMessagingTemplate webSocketMessagingTemplate;

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
	public ChatRoom findById(String id) {
		Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);

		return chatRoom.get();
	}

	@Override
	public ChatRoom join(ChatRoomUser joiningUser, ChatRoom chatRoom) {
		List<ChatRoomUser> users = chatRoom.getConnectedUsers();
		users.add(joiningUser);
		chatRoom.setConnectedUsers(users);
		chatRoomRepository.save(chatRoom);

		// send well come message

		// Update connected users list via websocket

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
	public List<ChatRoom> findAll() {
		return (List<ChatRoom>) chatRoomRepository.findAll();
	}

	@Override
	public void sendPublicMessage(Message message) {
		webSocketMessagingTemplate.convertAndSend(Destinations.ChatRoom.publicMessages(message.getChatRoomId()),
				message);

		this.messageService.appendInstantMessageToConversations(message);
	}

	@Override
	public void sendPrivateMessage(Message message) {
		webSocketMessagingTemplate.convertAndSendToUser(message.getToUser(),
				Destinations.ChatRoom.privateMessages(message.getChatRoomId()), message);

		webSocketMessagingTemplate.convertAndSendToUser(message.getFromUser(),
				Destinations.ChatRoom.privateMessages(message.getChatRoomId()), message);

		this.messageService.appendInstantMessageToConversations(message);
	}
	
	public void updateConnectedUsersViaWebSocket(ChatRoom chatRoom) {
		webSocketMessagingTemplate.convertAndSend(
				Destinations.ChatRoom.connectedUsers(chatRoom.getId()),
				chatRoom.getConnectedUsers());
	}
	
	

}
