package com.example.ChatTraductor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ChatTraductor.model.persistence.Message;
import com.example.ChatTraductor.model.service.MessageDTO;
import com.example.ChatTraductor.repository.IMessageRepository;
import com.example.ChatTraductor.security.persistance.User;
import com.example.ChatTraductor.security.repository.IUserRepository;

@Service
public class MessageService implements IMessageService{

	@Autowired
	IMessageRepository messageRepository;
	
	@Autowired
	IUserRepository userRepository;

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
		
		User sender = userRepository.findById(messageDTO.getSenderId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Creador no encontrado")
				);
		
		User receiver = userRepository.findById(messageDTO.getReceiverId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Creador no encontrado")
				);
		Message message = messageRepository.save(convertFromMessageDTOToDAO(messageDTO, sender, receiver));
		MessageDTO response = convertFromMessageDAOToDTO(message);

		return response;
	}


	private MessageDTO convertFromMessageDAOToDTO(Message message){
		// TODO Auto-generated method stub
		MessageDTO response = new MessageDTO(
				message.getId(), 
				message.getText(),
				message.getSenderId(),
				message.getReceiverId()
				);
		return response;
	}
	
	private Message convertFromMessageDTOToDAO(MessageDTO messageDTO, User sender, User receiver) {

		Message response = new Message(
				messageDTO.getId(), 
				messageDTO.getText(),
				messageDTO.getSenderId(),
				messageDTO.getReceiverId());
		
		response.setSender(sender);
		response.setReceiver(receiver);

		return response;
	}

}
