package com.mynotes.chatbot.dto;

public class ChatResponse {

	private String outputText;

	private String conversationId;

	public ChatResponse(String outputText, String conversationId) {
		super();
		this.outputText = outputText;
		this.conversationId = conversationId;
	}

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

}
