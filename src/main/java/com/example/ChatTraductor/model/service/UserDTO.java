package com.example.ChatTraductor.model.service;

import java.util.List;

public class UserDTO {

	private Integer id;
	private String name;
	private String surname;
	private String email;
	private String phoneNumber1;
	private List<MessageDTO> sentMessages;
	private List<MessageDTO> receivedMessages;

	public UserDTO(Integer id, String name, String surname, String email, String phoneNumber1) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber1 = phoneNumber1;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	public List<MessageDTO> getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(List<MessageDTO> sentMessages) {
		this.sentMessages = sentMessages;
	}
	public List<MessageDTO> getReceivedMessages() {
		return receivedMessages;
	}
	public void setReceivedMessages(List<MessageDTO> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
}
