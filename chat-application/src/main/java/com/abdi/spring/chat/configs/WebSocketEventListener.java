package com.abdi.spring.chat.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.abdi.spring.chat.entities.ChatRoomUser;
import com.abdi.spring.chat.services.ChatRoomService;

//check socket.messaging
public class WebSocketEventListener {
	
	@Autowired
	private ChatRoomService chatRoomService;
		
	@EventListener
	public void handleWebSocketConnectionListener(SessionConnectedEvent event) {
		System.out.println("Recieved a new web socket connection");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String chatRoomId = (String) headerAccessor.getSessionAttributes().get("chatRoomId");
		ChatRoomUser leavingUser = new ChatRoomUser(event.getUser().getName());
		
		//chatRoomService.leave(leavingUser, chatRoomService.findById(chatRoomId).get());
		
		
	}
}
