package com.example.ChatTraductor.model.service;

import java.util.ArrayList;
import java.util.List;

public class ChatDTO {
	private Integer id;
	private String name;
	private List<UserDTO> users = new ArrayList<>();
	//FIXME NECESITO ESTO?
	private List<MessageDTO> messages = new ArrayList<>();

	public ChatDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<UserDTO> getUsers() {
		return users; 
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	public List<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

}
