package com.example.ChatTraductor.model.persistence;

import java.util.List;

import com.example.ChatTraductor.security.persistance.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "chats")
public class Chat{

	@EmbeddedId
    private UsersChatRelation usersChatIds;
	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length=60)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user1_id", foreignKey = @ForeignKey(name = "Fk_user1_id" ))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("userId1")
	private User user1;

	@Column(name = "user1_id", insertable = false, updatable = false)
	private Integer user1Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user2_id", foreignKey = @ForeignKey(name = "Fk_user2_id" ))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@MapsId("userId2")
	private User user2;

	@Column(name = "user2_id", insertable = false, updatable = false)
	private Integer user2Id;

	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Message> messages;

	
	public Chat() {}

	public Chat(Integer id, String name, User user1, Integer user1Id, User user2,
			Integer user2Id, List<Message> messages) {
		super();
		this.id = id;
		this.name = name;
		this.user1 = user1;
		this.user1Id = user1Id;
		this.user2 = user2;
		this.user2Id = user2Id;
		this.messages = messages;
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
	public UsersChatRelation getUsersChatIds() {
		return usersChatIds;
	}

	public void setUsersChatIds(UsersChatRelation usersChatIds) {
		this.usersChatIds = usersChatIds;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public Integer getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(Integer user1Id) {
		this.user1Id = user1Id;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public Integer getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(Integer user2Id) {
		this.user2Id = user2Id;
	}
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	
	
}
