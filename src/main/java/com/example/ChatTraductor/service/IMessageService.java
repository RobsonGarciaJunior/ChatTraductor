package com.example.ChatTraductor.service;

import java.util.List;

import com.example.ChatTraductor.model.service.MessageDTO;

public interface IMessageService {

	List<MessageDTO> findAllMessagesByChatId(Integer chatId);

	List<MessageDTO> findAll(Integer id, Integer userId);

	MessageDTO createMessage(MessageDTO messageDTO);
	
	//MessageDTO updateMessage(MessageDTO messageDTO) throws MessageNotFoundException, IOException;
}
