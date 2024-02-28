package com.example.ChatTraductor.model.socket;

import com.example.ChatTraductor.enums.MessageType;

public class MessageFromServer {
	
	private MessageType messageType;
	private Integer room;
	private String message;
	private String authorName;
	private Integer authorId;

	public MessageFromServer() {
		super();
	}

	public MessageFromServer(MessageType messageType,Integer room, String message, String authorName, Integer authorId) {
		super();
		this.messageType = messageType;
		this.room = room;
		this.message = message;
		this.authorName = authorName;
		this.authorId = authorId;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

}