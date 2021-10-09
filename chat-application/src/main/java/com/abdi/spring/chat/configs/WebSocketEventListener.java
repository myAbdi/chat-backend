package com.abdi.spring.chat.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.abdi.spring.chat.entities.ChatRoomUser;
import com.abdi.spring.chat.services.ChatRoomService;

//check socket.messaging
@Component
public class WebSocketEventListener {
	
	@Autowired
	private ChatRoomService chatRoomService;
		
	@EventListener
	public void handleWebSocketConnectionListener(SessionConnectEvent event) {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String chatRoomId = headers.getNativeHeader("chatRoomId").get(0);
		headers.getSessionAttributes().put("chatRoomId", chatRoomId);
		ChatRoomUser joiningUser = new ChatRoomUser(event.getUser().getName());

		chatRoomService.join(joiningUser, chatRoomService.findById(chatRoomId));
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String chatRoomId = (String) headerAccessor.getSessionAttributes().get("chatRoomId");
		ChatRoomUser leavingUser = new ChatRoomUser(event.getUser().getName());
		
		chatRoomService.leave(leavingUser, chatRoomService.findById(chatRoomId));
		
		
	}
}
