package com.message.q.ajp.msgqajp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.message.q.ajp.msgqajp.service.MessageQueueService;

@RestController
public class MessageQueueController {
	
	private MessageQueueService msgQ = new MessageQueueService();
	
	@GetMapping("/messages")
	public List<String> getAllMessages() {
		return msgQ.getMessages();
	}
	
	@PostMapping("/messages/new")
	public void addNewMessage(@RequestBody String msg)  {
		msgQ.postMessage(msg);
		return;
	}
}
