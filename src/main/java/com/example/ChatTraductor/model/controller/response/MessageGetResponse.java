package com.example.ChatTraductor.model.controller.response;

public class MessageGetResponse {
	private Integer id;
	private String text;
	private Integer senderId;
	private Integer receiverId;
	
	public MessageGetResponse() {
		
	}

	public MessageGetResponse(Integer id, String text, Integer senderId, Integer receiverId) {
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
	
	public Integer getSenderId() {
		return senderId;
	}
	
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

}
