package com.mynotes.chatbot.repo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.ibm.watson.developer_cloud.assistant.v1.model.Context;

@RedisHash("BotContext")
public class BotContext implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String conersationId;
	
	private Context contex;
	
	public BotContext() {
	}

	public BotContext(String conersationId, Context contex) {
		super();
		this.conersationId = conersationId;
		this.contex = contex;
	}

	public String getConersationId() {
		return conersationId;
	}

	public void setConersationId(String conersationId) {
		this.conersationId = conersationId;
	}

	public Context getContex() {
		return contex;
	}

	public void setContex(Context contex) {
		this.contex = contex;
	}

}
