package com.abdi.spring.chat.entities;

import com.abdi.spring.chat.util.SystemUsers;

public class MessageBuilder {
	
	private Message message;
	private MessageChatRoom messageChatRoom;
	private MessageType messageType;
	private MessageText messageText;
	private MessageFromUser messageFromUser;
	private MessageToUser messageToUser;
	
	public MessageBuilder() {
		
	}
	
	public MessageChatRoom newMessage() {
		message = new Message();
		messageChatRoom = new MessageChatRoom();
		return messageChatRoom;
	}
	
	public class MessageChatRoom {
		public MessageType withChatRoomId(String chatRoomId) {
			message.setChatRoomId(chatRoomId);
			messageType = new MessageType();
			return messageType;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class MessageType {
		public MessageText systemMessage(){
			message.setFromUser(SystemUsers.ADMIN.getUsername());
			messageText = new MessageText();
			return messageText;
		}
		
		public MessageFromUser publicMessage() {
			message.setToUser(null);
			messageFromUser = new MessageFromUser();
			return messageFromUser;
		}
		
		public MessageToUser privateMessage(){
			messageToUser = new MessageToUser();
			return messageToUser;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class MessageToUser {
		public MessageFromUser toUser(String username) {
			message.setToUser(username);
			messageFromUser = new MessageFromUser();
			return messageFromUser;
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class MessageFromUser {
		public MessageText fromUser(String username) {
			message.setFromUser(username);
			messageText = new MessageText();
			return messageText;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class MessageText {
		public Message withText(String text) {
			message.setText(text);
			return message;
		}
	}
}