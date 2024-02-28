package com.example.ChatTraductor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ChatTraductor.model.service.MessageDTO;

@Service
public class MessageService implements IMessageService{

	@Override
	public List<MessageDTO> findAllMessagesByChatId(Integer chatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageDTO> findAll(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDTO createMessage(MessageDTO messageDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
