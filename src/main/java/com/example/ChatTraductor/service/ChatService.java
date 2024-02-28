package com.example.ChatTraductor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ChatTraductor.model.service.ChatDTO;

@Service
public class ChatService implements IChatService{

	@Override
	public List<ChatDTO> findAll(Integer id, Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO createChat(ChatDTO chatDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO deleteChat(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer canEnterUserChat(Integer idChat, Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer canDeleteChat(Integer idChat, Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsOnChat(Integer idChat, Integer idUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
