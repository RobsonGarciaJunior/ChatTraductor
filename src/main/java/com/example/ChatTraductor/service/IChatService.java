package com.example.ChatTraductor.service;

import java.util.List;

import com.example.ChatTraductor.model.service.ChatDTO;


public interface IChatService {

	List<ChatDTO> findAll(Integer id, Integer idUser);
	
	ChatDTO findById(Integer id);

	ChatDTO findByName(String name);

	ChatDTO createChat(ChatDTO chatDTO);

	ChatDTO deleteChat(Integer id, Integer userId);

	Integer canEnterUserChat(Integer idChat, Integer idUser);

	Integer canDeleteChat(Integer idChat, Integer idUser);

	boolean existsOnChat(Integer idChat, Integer idUser);		
}
