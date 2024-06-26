package com.example.ChatTraductor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ChatTraductor.model.persistence.Message;

public interface IMessageRepository extends CrudRepository<Message,Integer>{
	
	@Query(value = "SELECT * FROM messages m WHERE m.sender_id = :id OR m.receiver_id = :id", nativeQuery = true)
	Iterable<Message> findAllByForId(Integer id);
	
    //@Query(value = "SELECT * FROM messages m WHERE (m.sender_id = :id AND m.receiver_id = :id2) OR (m.sender_id = :id2 AND m.receiver_id = :id)", nativeQuery = true)
	@Query(value = "SELECT * FROM messages m WHERE (m.sender_id = :id AND m.receiver_id = :id2) OR (m.sender_id = :id2 AND m.receiver_id = :id)", nativeQuery = true)
	Iterable<Message> findMessagesFromChatters(Integer id, Integer id2);

}
