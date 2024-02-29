package com.example.ChatTraductor.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ChatTraductor.security.persistance.User;


public interface IUserRepository extends CrudRepository<User, Integer>{
	Optional<User> findByEmail(String email);
	
//	@Query(value="SELECT * FROM users u JOIN chats c ON u.id = c.user1_id OR u.id = c.user2_id WHERE u.id = :id", nativeQuery = true)
//    Optional<User> findUserWithChatsById(@Param("id") Integer idUser);
	
	//@Query("SELECT u FROM User u JOIN u.chats c WHERE c.id = :chatId")
	@Query(value = "SELECT u.* FROM users u " +
            "JOIN user_chat uc ON u.id = uc.user_id " +
            "WHERE uc.chat_id = :chatId", nativeQuery = true)
	Iterable<User> findAllUsersByChatId(@Param("chatId") Integer chatId);
	
	@Query("SELECT count(u) FROM User u  WHERE u.email = :email")
	Integer findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.id > :givenId")
	Iterable<User> findAllUsersCreatedAfterId(@Param("givenId") Integer givenId);

	
}
