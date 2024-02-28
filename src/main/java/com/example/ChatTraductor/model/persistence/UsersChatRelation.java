package com.example.ChatTraductor.model.persistence;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsersChatRelation implements Serializable {
	private static final long serialVersionUID = -3850939490342511370L;

	private Integer userId1;
	private Integer userId2;

	public UsersChatRelation() {
		super();
	}
	public UsersChatRelation(Integer userId1, Integer userId2) {
		super();
		this.userId1 = userId1;
		this.userId2 = userId2;
	}
	public Integer getUserId1() {
		return userId1;
	}
	public void setUserId1(Integer userId1) {
		this.userId1 = userId1;
	}
	public Integer getUserId2() {
		return userId2;
	}
	public void setUserId2(Integer userId2) {
		this.userId2 = userId2;
	} 


}
