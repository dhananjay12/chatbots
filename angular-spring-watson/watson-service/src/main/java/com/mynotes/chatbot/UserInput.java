package com.mynotes.chatbot;

import javax.validation.constraints.NotNull;

public class UserInput {
	
	@NotNull
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
