package com.abdi.spring.chat.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.abdi.spring.chat.repositories.ChatRoomRepository;
import com.abdi.spring.chat.services.ChatRoomService;

@RestController
@RequestMapping("/ab")
public class ChatRoomController {
	
	@Autowired
	ChatRoomService chatRoomService;
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	@PostMapping("/chatroom")
	public ResponseEntity<ChatRoom> saveChatRoom(@RequestBody ChatRoom chatRoom) {
		
		ChatRoom savedChatRoom = chatRoomService.save(chatRoom);
		
		return new ResponseEntity<>(savedChatRoom, HttpStatus.CREATED);
	}
	
	@GetMapping("/getchatrooms")
	public ResponseEntity<Iterable<ChatRoom>> getChatRooms(){
		Iterable<ChatRoom> chatrooms = chatRoomService.findAll();
		return new ResponseEntity<>(chatrooms, HttpStatus.OK);
	}
	
	@GetMapping("/getchat/{id}")
	public ResponseEntity<Optional<ChatRoom>> findChatRoom(@PathVariable String id){
		Optional<ChatRoom> chatroom = chatRoomService.findById(id);
		if(chatroom.isPresent()) {
			return new ResponseEntity<>(chatroom, HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	@PutMapping("/adduser/{id}")
	public ResponseEntity<ChatRoom> addUserToChatroom(@PathVariable String id, @RequestBody ChatRoomUser user){
		Optional<ChatRoom> chatroom = chatRoomRepository.findById(id);
		if(chatroom.isPresent()) {
			ChatRoom chat = chatroom.get();
			ChatRoom updatedChatRoom = chatRoomService.join(user, chat);
			return new ResponseEntity<>(updatedChatRoom, HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/rmuser/{id}")
	public ResponseEntity<ChatRoom> removeUserFromChatroom(@PathVariable String id, @RequestBody ChatRoomUser user){
		Optional<ChatRoom> chatroom = chatRoomRepository.findById(id);
		if(chatroom.isPresent()) {
			ChatRoom chat = chatroom.get();
			ChatRoom updatedChatRoom = chatRoomService.leave(user, chat);
			return new ResponseEntity<>(updatedChatRoom, HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
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
