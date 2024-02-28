package com.example.ChatTraductor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ChatTraductor.model.persistence.Message;

public interface IMessageRepository extends CrudRepository<Message,Integer>{

	@Query("SELECT m FROM Message m WHERE m.chatId = :chatId")
	Iterable<Message> findAllMessagesByChatId(@Param("chatId") Integer chatId);

	@Query("SELECT m FROM Message m WHERE m.chatId IN (:userChatList)")
	Iterable<Message> findAllMessagesOfUser(@Param("userChatList") List<Integer> userChatIds);    

	@Query("SELECT m FROM Message m WHERE m.chatId IN (:userChatList) AND m.id > :givenId")
	Iterable<Message> findAllMessagesOfUserCreatedAfterId(@Param("givenId") Integer givenId, @Param("userChatList") List<Integer> userChatList);

}
