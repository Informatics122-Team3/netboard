package com.netboard.message;

public abstract class Message {
	String messageType;

	abstract String serialize();
	
}
