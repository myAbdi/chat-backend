package com.abdi.spring.chat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.abdi.spring.chat.entities.ChatRoom;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String>{

	
}
