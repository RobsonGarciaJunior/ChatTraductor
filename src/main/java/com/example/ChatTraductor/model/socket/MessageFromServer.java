package com.example.ChatTraductor.model.socket;

import com.example.ChatTraductor.enums.MessageType;

public class MessageFromServer {
	
	private MessageType messageType;
	private Integer id;
	private String message;
	private String senderName;
	private Integer senderId;
	private String receiverName;
	private Integer receiverId;

	public MessageFromServer() {
		super();
	}
	
	public MessageFromServer(MessageType messageType, String message, String senderName, Integer senderId,
			String receiverName, Integer receiverId) {
		super();
		this.messageType = messageType;
		this.message = message;
		this.senderName = senderName;
		this.senderId = senderId;
		this.receiverName = receiverName;
		this.receiverId = receiverId;
	}

	public MessageFromServer(MessageType messageType, Integer id, String message, String senderName, Integer senderId) {
		super();
		this.messageType = messageType;
		this.id = id;
		this.message = message;
		this.senderName = senderName;
		this.senderId = senderId;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}



}