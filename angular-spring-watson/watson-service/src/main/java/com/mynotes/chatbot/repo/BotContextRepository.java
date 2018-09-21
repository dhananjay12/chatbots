package com.mynotes.chatbot.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotContextRepository extends CrudRepository<BotContext, String> {}
