package com.example.ChatTraductor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatTraductor.model.controller.response.UserGetResponse;
import com.example.ChatTraductor.model.service.UserDTO;
import com.example.ChatTraductor.security.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userService;

	//ASK PREGUNTA SI HAY PROBLEMA EN EL LOG DEL SERVER
	@GetMapping("/findAll/{id}")
	public ResponseEntity<List<UserGetResponse>> findAllUsers(@PathVariable("id") Integer id){
		List <UserDTO> listUserDTO = userService.findAll(id);
		List<UserGetResponse> response = new ArrayList<UserGetResponse>(); 

		//Transform every DTO from the list to GetResponse
		for(UserDTO userDTO: listUserDTO) {
			if(userDTO != null){
				response.add(convertFromUserDTOToGetResponse(userDTO));
			}
		}
		return new ResponseEntity<List<UserGetResponse>>(response ,HttpStatus.OK);
	}

	private UserGetResponse convertFromUserDTOToGetResponse(UserDTO userDTO) {
		
		Long phoneNumberLong = Long.parseLong(userDTO.getPhoneNumber1());
		UserGetResponse response = new UserGetResponse(
				userDTO.getEmail(),
				userDTO.getId(),
				userDTO.getName(),
				userDTO.getSurname(),
				phoneNumberLong);
		
		return response;
	}

}
