package com.netboard.message;


public class JoinMessage extends Message {
	private String guestIP;
	private String guestUsername;
	private String hostUsername;
	
	public JoinMessage(String guestIP, String guestUsername, String hostUsername) {
		super("join");
		this.guestIP = guestIP;
		this.guestUsername = guestUsername;
		this.hostUsername = hostUsername;
	}
	
	public String getGuestIP() {
		return this.guestIP;
	}
	
	public String getGuestUserName() {
		return this.guestUsername;
	}
	
	public String getHostUsername() {
		return this.hostUsername;
	}
}
