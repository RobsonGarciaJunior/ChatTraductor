package com.example.ChatTraductor.model.persistence;

import com.example.ChatTraductor.security.persistance.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "chat_id", foreignKey = @ForeignKey(name = "Fk_chat_id" ))
	@JoinColumns({
        @JoinColumn(name = "userId1", referencedColumnName="user1_id"),
        @JoinColumn(name = "userId2", referencedColumnName="user2_id")
    })
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Chat chat;

	@Column(name = "chat_id", insertable = false, updatable = false)
	private Integer chatId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "Fk_user_id" ))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer userId;
	
	public Message () {}

	public Message(Integer id, String text, Chat chat, Integer chatId, User user, Integer userId) {
		super();
		this.id = id;
		this.text = text;
		this.chat = chat;
		this.chatId = chatId;
		this.user = user;
		this.userId = userId;
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


	public Chat getChat() {
		return chat;
	}


	public void setChat(Chat chat) {
		this.chat = chat;
	}


	public Integer getChatId() {
		return chatId;
	}


	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
