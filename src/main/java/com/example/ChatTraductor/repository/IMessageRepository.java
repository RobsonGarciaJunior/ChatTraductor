package com.example.ChatTraductor.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ChatTraductor.model.persistence.Message;

public interface IMessageRepository extends CrudRepository<Message,Integer>{


}
