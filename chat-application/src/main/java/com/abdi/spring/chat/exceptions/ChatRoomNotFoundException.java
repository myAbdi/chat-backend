package com.abdi.spring.chat.exceptions;

public class ChatRoomNotFoundException extends RuntimeException {
	 
	private String message;

	public ChatRoomNotFoundException(String message) {
		super();
		this.message = message;
	}
}
