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
	@JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "Fk_sender_id" ))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User sender;

	@Column(name = "sender_id", insertable = false, updatable = false)
	private Integer senderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "Fk_receiver_id" ))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User receiver;

	@Column(name = "sender_id", insertable = false, updatable = false)
	private Integer receiverId;

	public Message () {}

	public Message(Integer id, String text, User sender, Integer senderId, User receiver, Integer receiverId) {
		super();
		this.id = id;
		this.text = text;
		this.sender = sender;
		this.senderId = senderId;
		this.receiver = receiver;
		this.receiverId = receiverId;
	}
	
	public Message(Integer id, String text, Integer senderId, Integer receiverId) {
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	

}
