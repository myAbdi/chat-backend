package com.abdi.spring.chat.controllers;


import org.springframework.web.bind.annotation.RestController;

import com.abdi.spring.chat.services.ChatRoomService;

@RestController
public class MessageController {
	
	private ChatRoomService chatRoomService; 
	
	
	/*@SubscribeMapping("/connected.users")
	public List<ChatRoomUser> listSubscribedUsers(SimpMessageHeaderAccessor headerAccessor) {
		String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
		if(chatRoomId == null ) {
			System.out.println("It is empty");
		} else {
			return chatRoomService.findById(chatRoomId).getConnectedUsers();
		}
		
		
		 return null;
		
	}*/
	
	/*@SubscribeMapping("/old.messages")
	public List<Message> listOldMessagesFromUser(Principal principal, SimpMessageHeaderAccessor headerAccessor){
		String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
		
		
		return null;
	}*/
	
	//post and push
	//message from user in the chat room
	
	
	
	//message from admin
	
	
}
