package com.netboard.message;


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
}
