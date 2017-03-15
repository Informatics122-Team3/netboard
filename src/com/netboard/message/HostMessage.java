package com.netboard.message;


public class HostMessage extends Message {
	private String hostIP;
	private String hostUsername;
	private String gameType;
	
	public HostMessage(String hostIP, String hostUsername, String gameType) {
		super("host");
		this.hostIP = hostIP;
		this.hostUsername = hostUsername;
		this.gameType = gameType;
	}
	
	public String getHostUsername() {
		return this.hostUsername;
	}
	
	public String getHostIP() {
		return this.hostIP;
	}
	
	public String getGameType() {
		return this.gameType;
	}
}
