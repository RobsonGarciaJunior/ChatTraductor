package com.example.ChatTraductor.security.model;

public class AuthResponse {
	
	private String email;
	private Integer id;
	private String name;
	private String surname;
	private Long phoneNumber1;
	private String accessToken;
	

	public AuthResponse() { }
	
	public AuthResponse(String email, String accessToken) {
		this.email = email;
		this.accessToken = accessToken;
	}

	public AuthResponse(String email, Integer id, String name, String surname, Long phoneNumber1, String accessToken) {
		super();
		this.email = email;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber1 = phoneNumber1;
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(Long phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
