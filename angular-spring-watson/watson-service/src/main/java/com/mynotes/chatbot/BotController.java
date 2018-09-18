package com.mynotes.chatbot;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;



@RestController
public class BotController {
	
	@Value("${flower.shop.workspace.id}")
	private String workspaceId;

	@Autowired
	protected Assistant assist;

	@PostMapping("/query")
	public ResponseEntity<?> query(@Valid @RequestBody UserInput userInput) {
		
		InputData input = new InputData.Builder(userInput.getText()).build();
		MessageOptions aMessageOptions = new MessageOptions.Builder(workspaceId).input(input).build();
		
		MessageResponse aMessageResponse = assist.message(aMessageOptions).execute();

		return ResponseEntity.ok(aMessageResponse);
	}

}
