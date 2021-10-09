package com.abdi.spring.chat.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdi.spring.chat.entities.ChatRoom;
import com.abdi.spring.chat.entities.ChatRoomUser;
import com.abdi.spring.chat.entities.Message;
import com.abdi.spring.chat.repositories.ChatRoomRepository;
import com.abdi.spring.chat.services.ChatRoomService;
import com.abdi.spring.chat.services.MessageService;

@RestController
@RequestMapping("/ab")
public class ChatRoomController {
	
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	@Autowired
	MessageService messageService;
	
	@PostMapping("/chatroom")
	public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom) {
		
		ChatRoom savedChatRoom = chatRoomService.save(chatRoom);
		
		return new ResponseEntity<>(savedChatRoom, HttpStatus.CREATED);
	}
	
	@GetMapping("/getchatrooms")
	public ResponseEntity<Iterable<ChatRoom>> getChatRooms(){
		Iterable<ChatRoom> chatrooms = chatRoomService.findAll();
		return new ResponseEntity<>(chatrooms, HttpStatus.OK);
	}
	
	@GetMapping("/getchat/{id}")
	public ResponseEntity<ChatRoom> join(@PathVariable String id){
		ChatRoom chatroom = chatRoomService.findById(id);
		if(chatroom != null) {
			return new ResponseEntity<>(chatroom, HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@SubscribeMapping("/connected.users")
	public List<ChatRoomUser> listConnectedUsersOnSubscribe(SimpMessageHeaderAccessor headerAccessor){
		String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
		return chatRoomService.findById(chatRoomId).getConnectedUsers();
		
	}
	
	@SubscribeMapping("/old.messages")
	public List<Message> listOldMessageFromUserOnSubscribe(Principal principal, SimpMessageHeaderAccessor headerAccessor){
		String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
		
		return messageService.findAllInstantMessagesFor(principal.getName(), chatRoomId);
	}
	
	@MessageMapping("/send.message")
	public void sendMessage(@Payload Message message, Principal principal, SimpMessageHeaderAccessor headerAccessor) {
		String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
		message.setFromUser(principal.getName());
		message.setChatRoomId(chatRoomId);
		if (message.isPublic()) {
			this.chatRoomService.sendPublicMessage(message);
		}
		this.chatRoomService.sendPrivateMessage(message);
	}
	
	
	/*@PutMapping("/adduser/{id}")
	public ResponseEntity<ChatRoom> addUserToChatroom(@PathVariable String id, @RequestBody ChatRoomUser user){
		Optional<ChatRoom> chatroom = chatRoomRepository.findById(id);
		if(chatroom.isPresent()) {
			ChatRoom chat = chatroom.get();
			ChatRoom updatedChatRoom = chatRoomService.join(user, chat);
			return new ResponseEntity<>(updatedChatRoom, HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}*/
	
	/*@PutMapping("/rmuser/{id}")
	public ResponseEntity<ChatRoom> removeUserFromChatroom(@PathVariable String id, @RequestBody ChatRoomUser user){
		Optional<ChatRoom> chatroom = chatRoomRepository.findById(id);
		if(chatroom.isPresent()) {
			ChatRoom chat = chatroom.get();
			ChatRoom updatedChatRoom = chatRoomService.leave(user, chat);
			return new ResponseEntity<>(updatedChatRoom, HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}*/
	
	@DeleteMapping("chatroom/{id}")
	public ResponseEntity<ChatRoom> removeChatroom(@PathVariable String id){
		Optional<ChatRoom> chatroom = chatRoomRepository.findById(id);
		if(chatroom.isPresent()) {
			ChatRoom chat = chatroom.get();
			chatRoomService.delete(chat);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
