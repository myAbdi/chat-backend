package com.abdi.spring.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abdi.spring.chat.repositories.ChatRoomRepository;
import com.abdi.spring.chat.services.ChatRoomService;

@SpringBootTest
class ChatApplicationTests {

	@Autowired 
	 private ChatRoomService chatRoomService;
	@Autowired
	 private ChatRoomRepository chatRoomRepository;
	
	@Test
	void createChatRoom() {
		
		chatRoomRepository.deleteAll();
		//chatRoomService.save(new ChatRoom(null, "abdi11", "geleto11"));
		
	}

}
