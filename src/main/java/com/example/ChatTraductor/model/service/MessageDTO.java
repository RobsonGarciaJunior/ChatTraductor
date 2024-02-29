package com.example.ChatTraductor.model.service;

public class MessageDTO {
	private Integer id;
	private String text;
	private UserDTO sender;
	private Integer senderId;
	private UserDTO receiver;
	private Integer receiverId;

	public MessageDTO(Integer id, String text, UserDTO sender, Integer senderId, UserDTO receiver, Integer receiverId) {
		super();
		this.id = id;
		this.text = text;
		this.sender = sender;
		this.senderId = senderId;
		this.receiver = receiver;
		this.receiverId = receiverId;
	}
	
	public MessageDTO(Integer id, String text, Integer senderId, Integer receiverId) {
		super();
		this.id = id;
		this.text = text;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDTO getSender() {
		return sender;
	}

	public void setSender(UserDTO sender) {
		this.sender = sender;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public UserDTO getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDTO receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
}
