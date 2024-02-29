package com.example.ChatTraductor.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ChatTraductor.model.service.UserDTO;
import com.example.ChatTraductor.security.persistance.User;
import com.example.ChatTraductor.security.repository.IUserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	IUserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// esta es la funcion que busca al usuario por email. 
		// ya que en este caso el campo de login es el email
		// si fuese otro, realizar otra funcion
		return userRepository.findByEmail(username)
				.orElseThrow(
						() -> new UsernameNotFoundException("User " + username + " not found")
						);
	}

	@Override
	public List<UserDTO> findAll(Integer id) {

		List<UserDTO> response = new ArrayList<UserDTO>();


		if(id == 0) {
			Iterable<User> listUser = userRepository.findAll();
			for(User actualUser: listUser) {
				response.add(convertFromUserDAOToDTO(actualUser));
			}
		}else {
			Iterable<User> listUser = userRepository.findAllUsersCreatedAfterId(id);
			for(User actualUser: listUser) {
				response.add(convertFromUserDAOToDTO(actualUser));
			}
		}
		return response;	
	}


	@Override
	public UserDTO findById(Integer id) {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User " + id + " not found"));

		UserDTO response = convertFromUserDAOToDTO(user);
		return response;

	}

	@Override
	public List<UserDTO> findAllUsersByChatId(Integer chatId) {

		//		Chat chat = chatRepository.findById(chatId).orElseThrow(
		//				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Chat no encontrado")
		//				);

		Iterable<User> listUser= userRepository.findAllUsersByChatId(chatId);

		List<UserDTO> response = new ArrayList<UserDTO>();

		for(User user: listUser) {
			response.add(convertFromUserDAOToDTO(user));
		}

		return response;	
	}

	@Override
	public Integer findUserByEmail(String email) {
		Integer found=userRepository.findUserByEmail(email);
		return found;
	}


	private UserDTO convertFromUserDAOToDTO(User user) {
		UserDTO response = new UserDTO(
				user.getId(),
				user.getName(),
				user.getSurname(),
				user.getEmail(),
				user.getPhoneNumber1()
				);
		return response;
	}

	/////
}
