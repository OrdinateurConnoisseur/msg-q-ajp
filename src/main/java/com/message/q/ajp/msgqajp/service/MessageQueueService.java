package com.message.q.ajp.msgqajp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

import com.message.q.ajp.msgqajp.logging.MessageLogger;
import com.message.q.ajp.msgqajp.model.Message;

public class MessageQueueService {
	
	// I believe an async-safe implementation is necessary here. API should be able to 
	// enqueue messages from concurrent HTTP requests without concern for data corruption.
	private PriorityBlockingQueue<Message> pq;
	private MessageLogger ml;
	
	public MessageQueueService() {
		this.pq = new PriorityBlockingQueue<>();
		this.ml = new MessageLogger();
	}
	
	public List<String> getMessages() {
		long currTime = new Date().getTime();
		while(pq.peek() != null && pq.peek().getExpiry() <= currTime) pq.poll();
		return formatQueuedMsgs(currTime);
	}

	public void postMessage(String c) {
		ml.logMsg(c);
		pq.add(new Message(new Date(), c));
	}
	
	private List<String> formatQueuedMsgs(long ct) {
		ArrayList<Message> messages = new ArrayList<>(pq);
		messages.sort(null); // Trade a bit of performance for readability
		return messages.stream().map(m -> m.toString(ct)).collect(Collectors.toList());
	}
}
