package com.example.ChatTraductor.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ChatTraductor.model.persistence.Chat;

public interface IChatRepository extends CrudRepository<Chat, Integer> {

	

}
