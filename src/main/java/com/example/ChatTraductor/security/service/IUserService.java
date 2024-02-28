package com.example.ChatTraductor.security.service;

import java.util.List;

import com.example.ChatTraductor.model.service.UserDTO;

public interface IUserService {
	List<UserDTO> findAll(Integer id);
	UserDTO findById(Integer id);
	List<UserDTO> findAllUsersByChatId(Integer chatId);
	Integer findUserByEmail(String email);
}
