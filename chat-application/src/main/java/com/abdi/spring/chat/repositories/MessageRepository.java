package com.abdi.spring.chat.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.abdi.spring.chat.entities.Key;
import com.abdi.spring.chat.entities.Message;

@Repository
public interface MessageRepository extends CassandraRepository <Message, Key> {

	//List<Message> findByKeyUsernameAndKeyChatRoomId(String username, String chatRoomId);
}
