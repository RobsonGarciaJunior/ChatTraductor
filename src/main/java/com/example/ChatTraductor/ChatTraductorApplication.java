package com.example.ChatTraductor;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ChatTraductor.security.persistance.User;
import com.example.ChatTraductor.security.repository.IUserRepository;
import com.example.ChatTraductor.service.IMessageService;
import com.github.javafaker.Faker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@SpringBootApplication
public class ChatTraductorApplication {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IMessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(ChatTraductorApplication.class, args);
	}

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		Iterable<User> u = userRepository.findAll();
		List<User> users = (List<User>) u;

		if(users.size() == 0) {
			for(int i = 0; i < 10 ; i++) {	
				PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
				String[] region = {"ES", "GB", "PT"};
				Random random = new Random();
				int index = random.nextInt(region.length);
				seedUsersTable(phoneNumberUtil, region[index]);
			}
			System.out.println("USERS SEED");
		} else {
			System.out.println("USER NOT SEED");
		}
	}

	private void seedUsersTable(PhoneNumberUtil phoneNumberUtil, String region) {
		String lng = getLanguage(region);
		Faker faker = new Faker(new Locale(lng + "-" + region));
		
		PhoneNumber exampleNumber = phoneNumberUtil.getExampleNumberForType(region, PhoneNumberUtil.PhoneNumberType.MOBILE);
		String phone = String.valueOf("+"+String.valueOf(exampleNumber.getCountryCode()) + String.valueOf(exampleNumber.getNationalNumber()));  
		System.out.println( "CREATED: " + region + phone);

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		User user = new User();	
		user.setName(firstName);
		user.setSurname(lastName);
		user.setEmail(firstName+lastName+"@elorrieta.com");
		user.setPassword(new BCryptPasswordEncoder().encode("elorrieta"));
		user.setPhoneNumber1(phone);
		userRepository.save(user);
	}

	private String getLanguage(String region) {
		String response = new String();
		switch (region) {
		case "ES":
			response = "es";
			break;
		case "GB":
			response = "en";
			break;
		case "PT":
			response = "pt";
			break;
		}
		return response;
	}
}
