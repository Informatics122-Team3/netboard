package com.netboard.message;

import java.io.Serializable;

public class Message implements Serializable {
	private String messageType;
	
	public Message(String messageType) {
		this.messageType = messageType;
	}
	
	public String getMessageType() {
		return messageType;
	}
}
