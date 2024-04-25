package com.example.ChatTraductor.service;

import java.util.List;

import com.example.ChatTraductor.model.service.MessageDTO;

public interface IMessageService {

	List<MessageDTO> findAllMessagesByChatId(Integer chatId);

	List<MessageDTO> findAll(Integer userId);

	MessageDTO createMessage(MessageDTO messageDTO);

	String translateMessage(String text, Integer senderId, Integer receiverId);
	
	//MessageDTO updateMessage(MessageDTO messageDTO) throws MessageNotFoundException, IOException;
}
