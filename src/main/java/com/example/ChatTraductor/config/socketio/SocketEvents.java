package com.example.ChatTraductor.config.socketio;

public enum SocketEvents {

	ON_MESSAGE_RECEIVED("send message"),
	ON_SEND_MESSAGE("receive message"),
	ON_MESSAGE_NOT_SENT("not sent message");
	
	public final String value;

	private SocketEvents(String value) {
		this.value = value;
	}
}