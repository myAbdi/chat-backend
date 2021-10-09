package com.abdi.spring.chat.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

import com.abdi.spring.chat.entities.ChatRoom;
import com.abdi.spring.chat.entities.ChatRoomUser;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.github.javafaker.Faker;

@DataRedisTest
class ChatRoomRepositoryTest {

	@Autowired
	ChatRoomRepository chatRoomRepository;

	List<ChatRoomUser> chatRoomUsers = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testChatRoomRepository() {
		/*chatRoomRepository.deleteAll();
		Faker faker = new Faker(Locale.getDefault());
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {
				ChatRoomUser chatRoomUser = new ChatRoomUser(faker.name().username());

				chatRoomUsers.add(chatRoomUser);
			}

			ChatRoom chatroom = new ChatRoom(Uuids.timeBased().toString(), faker.space().galaxy(),
					faker.lorem().characters(true), chatRoomUsers);

			this.chatRoomRepository.save(chatroom);

		}*/
		System.out.println(chatRoomRepository.count());
		chatRoomRepository.findAll().forEach(System.out::println);
		
	}

}
