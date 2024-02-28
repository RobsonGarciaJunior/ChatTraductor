package com.example.ChatTraductor.config.socketio;

public enum SocketEvents {

	ON_MESSAGE_RECEIVED("send message"),
	ON_SEND_MESSAGE("receive message"),
	ON_MESSAGE_NOT_SENT("not sent message"), 
	
	ON_CHAT_RECEIVED("send chat"),
	ON_SEND_CHAT("receive chat"),
	ON_CHAT_NOT_SENT("not sent chat");
	
	public final String value;

	private SocketEvents(String value) {
		this.value = value;
	}
}