package com.netboard.message;

import com.google.gson.Gson;

public class HostMessage extends Message {
	protected String hostIP;
	protected String hostUsername;
	protected String gameType;
	protected HostMessage(String hostIP, String hostUsername, String gameType) {
		messageType = "host";
		this.hostIP = hostIP;
		this.hostUsername = hostUsername;
		this.gameType = gameType;
	}
	protected String serialize() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
