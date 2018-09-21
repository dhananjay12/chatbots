package com.mynotes.chatbot.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.mynotes.chatbot.dto.ChatRequest;
import com.mynotes.chatbot.dto.ChatResponse;
import com.mynotes.chatbot.repo.BotContext;
import com.mynotes.chatbot.repo.BotContextRepository;

@RestController
public class BotController {

	@Value("${flower.shop.workspace.id}")
	private String workspaceId;

	// Dont do this at home
	private Map<String, Context> myMap = new ConcurrentHashMap<>();

	@Autowired
	protected Assistant assist;

	@Autowired
	protected BotContextRepository repo;

	@PostMapping("/chat")
	public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {

		MessageResponse aMessageResponse = null;

		if (StringUtils.isEmpty(chatRequest.getConversationId())) {
			aMessageResponse = callWatson(chatRequest);
			// BotContext aBotContext = new
			// BotContext(aMessageResponse.getContext().getConversationId(),
			// aMessageResponse.getContext());
			// repo.save(aBotContext);
			myMap.put(aMessageResponse.getContext().getConversationId(), aMessageResponse.getContext());

		} else {
			// MessageOptions aMessageOptions = new
			// MessageOptions.Builder(workspaceId).input(input)
			// .context(repo.findById(chatRequest.getConversationId()).get().getContex()).build();

			aMessageResponse = callWatson(chatRequest);
		}

		return ResponseEntity.ok(new ChatResponse(aMessageResponse.getOutput().getText().get(0),
				aMessageResponse.getContext().getConversationId()));
	}

	private MessageResponse callWatson(ChatRequest chatRequest) {
		InputData input = new InputData.Builder(chatRequest.getInputText()).build();
		MessageOptions aMessageOptions = new MessageOptions.Builder(workspaceId).input(input)
				.context(myMap.get(chatRequest.getConversationId())).build();

		MessageResponse aMessageResponse = assist.message(aMessageOptions).execute();
		return aMessageResponse;
	}

}
