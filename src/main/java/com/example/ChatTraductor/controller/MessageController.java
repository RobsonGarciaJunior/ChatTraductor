package com.example.ChatTraductor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatTraductor.model.controller.response.MessageGetResponse;
import com.example.ChatTraductor.model.service.MessageDTO;
import com.example.ChatTraductor.security.persistance.User;
import com.example.ChatTraductor.service.MessageService;

@RestController
@RequestMapping("api/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@GetMapping("findAll")
	public ResponseEntity<List<MessageGetResponse>> getMessages(Authentication authentication) throws IOException{

		User user = (User) authentication.getPrincipal();

		List <MessageDTO> listMessageDTO = messageService.findAll(user.getId());
		List<MessageGetResponse> response = new ArrayList<MessageGetResponse>(); 

		//Transform every DTO from the list to GetResponse
		for(MessageDTO messageDTO: listMessageDTO) {
			response.add(convertFromMessageDTOToGetResponse(messageDTO));
		}
		return new ResponseEntity<List<MessageGetResponse>>(response,HttpStatus.OK);
	}

	private MessageGetResponse convertFromMessageDTOToGetResponse(MessageDTO messageDTO) {
		
		MessageGetResponse response = new MessageGetResponse(
				messageDTO.getId(), 
				messageDTO.getText(),	
				messageDTO.getSenderId(),
				messageDTO.getReceiverId()
				);
		return response;
	}	
}
