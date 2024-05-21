package com.example.ChatTraductor.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ChatTraductor.model.persistence.Message;
import com.example.ChatTraductor.model.service.MessageDTO;
import com.example.ChatTraductor.model.service.UserDTO;
import com.example.ChatTraductor.repository.IMessageRepository;
import com.example.ChatTraductor.security.persistance.User;
import com.example.ChatTraductor.security.repository.IUserRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

@Service
public class MessageService implements IMessageService {

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
	public List<MessageDTO> findAll(Integer userId) {
		List<MessageDTO> response = new ArrayList<MessageDTO>();

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado"));

		UserDTO userDTO = convertFromUserDAOToDTO(user);

		Iterable<Message> listMessage = messageRepository.findAllByForId(userDTO.getId());
		for (Message actualMessage : listMessage) {
			System.out.println("sender:" + actualMessage.getSenderId() + " receiver:" + actualMessage.getReceiverId());
			MessageDTO messageDTO = convertFromMessageDAOToDTO(actualMessage);
			if (messageDTO.getSenderId() != userDTO.getId()) {
				String translatedMessage = translateMessage(messageDTO.getText(), messageDTO.getSenderId(),
						messageDTO.getReceiverId());
				System.out.println("AAAAAAA:" + translatedMessage);
				messageDTO.setText(translatedMessage);
			}
			response.add(messageDTO);
		}
		return response;
	}

	@Override
	public List<MessageDTO> findMessagesFromChatters(Integer chatter1Id, Integer chatter2Id) {
		List<MessageDTO> response = new ArrayList<MessageDTO>();

		User chatter1 = userRepository.findById(chatter1Id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado"));

		User chatter2 = userRepository.findById(chatter2Id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado"));

		UserDTO chatter1DTO = convertFromUserDAOToDTO(chatter1);
		UserDTO chatter2DTO = convertFromUserDAOToDTO(chatter2);

		Iterable<Message> listMessage = messageRepository.findMessagesFromChatters(chatter1DTO.getId(),
				chatter2DTO.getId());
		for (Message actualMessage : listMessage) {
			System.out.println("sender:" + actualMessage.getSenderId() + " receiver:" + actualMessage.getReceiverId());
			MessageDTO messageDTO = convertFromMessageDAOToDTO(actualMessage);
			if (messageDTO.getSenderId() != chatter1DTO.getId()) {
				String translatedMessage = translateMessage(messageDTO.getText(), messageDTO.getSenderId(),
						messageDTO.getReceiverId());
				System.out.println("AAAAAAA:" + translatedMessage);
				messageDTO.setText(translatedMessage);
			}
			response.add(messageDTO);
		}
		return response;
	}

	@Override
	public MessageDTO createMessage(MessageDTO messageDTO) {

		User sender = userRepository.findById(messageDTO.getSenderId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Creador no encontrado"));

		User receiver = userRepository.findById(messageDTO.getReceiverId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Creador no encontrado"));
		Message message = messageRepository.save(convertFromMessageDTOToDAO(messageDTO, sender, receiver));
		MessageDTO response = convertFromMessageDAOToDTO(message);

		return response;
	}

	public String translateMessage(String text, Integer senderId, Integer receiverId) {
		String response = "";

		User senderDAO = userRepository.findById(senderId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado"));

		User receiverDAO = userRepository.findById(receiverId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado"));

		UserDTO sender = convertFromUserDAOToDTO(senderDAO);
		UserDTO receiver = convertFromUserDAOToDTO(receiverDAO);

		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

		try {
			Phonenumber.PhoneNumber senderProto = phoneNumberUtil.parse(sender.getPhoneNumber1().toString(), "");

			Phonenumber.PhoneNumber receiverProto = phoneNumberUtil.parse(receiver.getPhoneNumber1().toString(), "");

			String targetLang = getLanguage(receiverProto.getCountryCode());
			String sourceLang = getLanguage(senderProto.getCountryCode());

			System.out.println("SENDER Country code: " + senderProto.getCountryCode());
			System.out.println("RECEIVER Country code: " + receiverProto.getCountryCode());
			System.out.println("BB: " + senderId);
			System.out.println("BB: " + receiverId);
			// This prints "Country code: 91"
			if (sourceLang != targetLang) {
				System.out.println("PRIMER IF");
				String translatedText = translateText(text, sourceLang, targetLang);
				System.out.println("Translated text: " + translatedText);
				response = translatedText;
			} else {
				System.out.println("ELSEELSELELSE");
				response = text;
			}
			return response;
		} catch (NumberParseException e) {
			System.err.println("NumberParseException was thrown: " + e.toString());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return response;
	}

	public static String translateText(String text, String sourceLang, String targetLang) throws IOException {
		String encodedText = URLEncoder.encode(text, "UTF-8");
		String encodedLangPair = URLEncoder.encode(sourceLang + "|" + targetLang, "UTF-8");
		String url = "https://www.apertium.org/apy/translate?q=" + encodedText + "&langpair=" + encodedLangPair;
		System.out.println("Lengua mandada: " + sourceLang);
		System.out.println("Lengua a traducir: " + targetLang);
		// Fetch the response as a JSON object
		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		JSONObject json = new JSONObject(doc.text());
		String translatedText = json.getJSONObject("responseData").getString("translatedText");

		System.out.println("Translated text: " + translatedText);
		return translatedText;
	}

	private String getLanguage(Integer countryCode) {
		String response = new String();
		switch (countryCode) {
		case 351:
			response = "por";
			break;
		case 34:
			response = "es";
			break;
		case 44:
			response = "en";
			break;
		}
		return response;
	}

	private MessageDTO convertFromMessageDAOToDTO(Message message) {
		// TODO Auto-generated method stub
		MessageDTO response = new MessageDTO(message.getId(), message.getText(), message.getSenderId(),
				message.getReceiverId());

		return response;
	}

	private Message convertFromMessageDTOToDAO(MessageDTO messageDTO, User sender, User receiver) {

		Message response = new Message(messageDTO.getId(), messageDTO.getText(), messageDTO.getSenderId(),
				messageDTO.getReceiverId());

		response.setSender(sender);
		response.setReceiver(receiver);

		return response;
	}

	private UserDTO convertFromUserDAOToDTO(User user) {
		// TODO Auto-generated method stub
		UserDTO response = new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(),
				user.getPhoneNumber1());
		return response;
	}

}
