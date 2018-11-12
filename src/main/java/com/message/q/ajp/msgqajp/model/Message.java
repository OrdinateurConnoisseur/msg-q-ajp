package com.message.q.ajp.msgqajp.model;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Message implements Comparable<Message>{
	static final int tenSec = 10000; // in milliseconds
	static final int oneMin = 60000; // in milliseconds
	
	Date arrival;
	String content;
	long expiry;
	public Message(Date arrival,String content) {
		super();
		this.arrival = arrival;
		this.content = content;
		this.expiry  = arrival.getTime() + ThreadLocalRandom.current().nextInt(tenSec, oneMin + 1);
	}

	public Date getArrival() {
		return arrival;
	}
		
	public long getExpiry() {
		return expiry;
	}

	public String getContent() {
		return content;
	}
	
	public String toString(long currTime) {
		// Format message content w/ remaining time in Queue
		return String.format("Expires in %.3f seconds:%s", (expiry - currTime)/1000.0, content); 
	}
	
	@Override
	public int compareTo(Message b) {
		return Long.compare(this.getExpiry(), b.getExpiry());
	}
}
