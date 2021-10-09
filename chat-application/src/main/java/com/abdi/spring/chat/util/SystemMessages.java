package com.abdi.spring.chat.util;

import com.abdi.spring.chat.entities.Message;
import com.abdi.spring.chat.entities.MessageBuilder;

public class SystemMessages {
	
	public static final Message welcome(String chatRoomId, String username) {
		return new MessageBuilder()
				.newMessage()
				.withChatRoomId(chatRoomId)
				.systemMessage()
				.withText(username + " joined us :)");
	}

	public static final Message goodbye(String chatRoomId, String username) {
		return new MessageBuilder()
				.newMessage()
				.withChatRoomId(chatRoomId)
				.systemMessage()
				.withText(username + " left us :(");
	}
}
