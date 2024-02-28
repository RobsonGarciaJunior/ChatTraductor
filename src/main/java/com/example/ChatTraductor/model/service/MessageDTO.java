package com.example.ChatTraductor.model.service;

public class MessageDTO {
	private Integer id;
	private String text;
	private ChatDTO chat;
	private Integer chatId;
	private UserDTO user;
	private Integer userId;

	public MessageDTO(Integer id, String text, ChatDTO chat, UserDTO user) {
		super();
		this.id = id;
		this.text = text;
		this.chat = chat;
		this.user = user;
	}
	public MessageDTO(Integer id, String text, Integer chatId,  Integer userId) {
		super();
		this.id = id;
		this.text = text;
		this.chatId = chatId;
		this.userId = userId;
	}

	public MessageDTO(Integer id, String text) {
		super();
		this.id = id;
		this.text = text;
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

	public ChatDTO getChat() {
		return chat;
	}

	public void setChat(ChatDTO chat) {
		this.chat = chat;
	}

	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
