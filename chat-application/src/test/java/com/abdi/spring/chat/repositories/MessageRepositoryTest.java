package com.abdi.spring.chat.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.abdi.spring.chat.entities.Message;
import com.github.javafaker.Faker;
import com.datastax.oss.driver.api.core.uuid.Uuids;

@DataCassandraTest
class MessageRepositoryTest {

	@Autowired
	private MessageRepository messageRepository;
	
	@BeforeEach
	void setUp() throws Exception {

		/*messageRepository.deleteAll();

		Faker faker = new Faker(Locale.getDefault());
		for (int i = 0; i < 100; i++) {

			Message message = new Message(faker.name().username(), faker.color().name(),
					faker.date().birthday(), faker.name().firstName(), faker.name().lastName(),
					faker.lorem().characters(true));
			
			this.messageRepository.save(message);
			
		}*/
		// System.out.println(message);
	}

	@Test
	void testFindByUsernameAndChatRoomId() throws ParseException {
		
		String pattern = "dd/MM/yyyy";
        String date = "10/10/1972";
		/*Faker faker = new Faker(Locale.getDefault());
		for (int i = 0; i < 10; i++) {

			Message message = new Message(faker.name().username(), Uuids.timeBased().toString(),
					faker.date().birthday(), faker.name().firstName(), faker.name().lastName(),
					faker.lorem().characters(true));
			
			messages.add(message);
			
		}*/
		
        DateFormat df = new SimpleDateFormat(pattern);
		List<Message> p = messageRepository.findByUsernameAndChatRoomId("somer.gorczany", "fuchsia");
		List<Message> datemessage = messageRepository.findByChatRoomIdAndDateAfter("fuchsia", df.parse(date));

		long p1 = messageRepository.count();
		System.out.println(p);
		System.out.println("''''''''''''''''''''''");
		System.out.println(datemessage);
		System.out.println(p1);
	}

}
