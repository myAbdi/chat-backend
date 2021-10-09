package com.abdi.spring.chat.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.stereotype.Repository;
import com.abdi.spring.chat.entities.Message;

@Repository
public interface MessageRepository extends MapIdCassandraRepository<Message> {
	
	List<Message> findByUsernameAndChatRoomId(String username, String chatRoomId);
	
	@AllowFiltering
	List<Message> findByChatRoomIdAndDateAfter(String chatRoomId, Date date); 
}
