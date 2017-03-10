package com.netboard.server;

import com.google.gson.Gson;

public abstract class Message {
	String messageType;

	abstract String serialize();
	
}
